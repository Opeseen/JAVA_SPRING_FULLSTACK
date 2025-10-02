import { useEffect, useReducer, createContext, useContext } from "react";

export const CartContext = createContext();

// // creating a cart custom hook to use
export const useCart = () => useContext(CartContext);

// defining action types
const ADD_TO_CART = "ADD_TO_CART";
const REMOVE_FROM_CART = "REMOVE_FROM_CART";
const CLEAR_CART = "CLEAR_CART";

const cartReducer = (state, action) => {
  switch (action.type) {
    case ADD_TO_CART: {
      const { product, quantity } = action.payload;
      const existingItem = state.find(
        (item) => item.productId === product.productId
      );

      if (existingItem) {
        return state.map((item) =>
          item.productId === product.productId
            ? {
                ...item,
                quantity: item.quantity + quantity,
              }
            : item
        );
      }
      return [...state, { ...product, quantity }];
    }
    case REMOVE_FROM_CART:
      return state.filter(
        (item) => item.productId !== action.payload.productId
      );
    case CLEAR_CART:
      return [];
    default:
      return state;
  }
};

export const CartProvider = ({ children }) => {
  const initialCartState = (() => {
    try {
      const storedCart = localStorage.getItem("cart");
      return storedCart ? JSON.parse(storedCart) : [];
    } catch (error) {
      console.error("Failed to parse cart from localStorage:", error);
      return [];
    }
  })();

  const [cart, dispatch] = useReducer(cartReducer, initialCartState);

  // save to the localStorage whenever it changes
  useEffect(() => {
    try {
      localStorage.setItem("cart", JSON.stringify(cart));
    } catch (error) {
      console.log("Failed to save cart to localStorage", error);
    }
  }, [cart]);

  const addToCart = (product, quantity) => {
    dispatch({ type: ADD_TO_CART, payload: { product, quantity } });
  };

  const removeFromCart = (productId) => {
    dispatch({ type: REMOVE_FROM_CART, payload: { productId } });
  };

  const clearCart = () => {
    dispatch({ type: CLEAR_CART });
  };

  const totalQuantity = cart.reduce((acc, item) => acc + item.quantity, 0);

  return (
    <CartContext
      value={{ cart, clearCart, addToCart, removeFromCart, totalQuantity }}
    >
      {children}
    </CartContext>
  );
};
