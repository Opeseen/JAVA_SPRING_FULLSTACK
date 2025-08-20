import { createContext, useState, useEffect, useContext } from "react";

export const CartContext = createContext();

// creating a cart custom hook to use
export const useCart = () => useContext(CartContext);

export const CartProvider = ({ children }) => {
  // Initialize cart state from local storage or as empty array
  const [cart, setCart] = useState(() => {
    try {
      const storedCart = localStorage.getItem("cart");
      // return the stored cart ... else return empty
      return storedCart ? JSON.parse(storedCart) : [];
    } catch (error) {
      console.log("Failed to parse cart from local storage", error);
      return [];
    }
  });

  // save the localStorage whenever it changes
  useEffect(() => {
    try {
      localStorage.setItem("cart", JSON.stringify(cart));
    } catch (error) {
      console.log("Failed to save cart to localStorage", error);
    }
  }, [cart]);

  const addToCart = (product, quantity) => {
    // set the new cart
    setCart((prevCart) => {
      // check if the cart already exist in the cart
      const existingItems = prevCart.find(
        (item) => item.productId === product.productId
      );

      // check if the items was found in the cart already
      if (existingItems) {
        // update with the updated quantity
        return prevCart.map((item) =>
          // check if the given productID is same as the received productID then update the quantity
          //  with the new qty value else simply return the items(not making any modifications )
          item.productId === product.productId
            ? { ...existingItems, quantity: item.quantity + quantity }
            : item
        );
      }

      // add the product if not in the cart by creating a new array with the previous items of the cart and the newly added items
      return [...prevCart, { ...product, quantity }];
    });
  };

  const removeFromCart = (productId) => {
    setCart((prevCart) =>
      prevCart.filter((item) => item.productId !== productId)
    );
  };

  const totalQuantity = cart.reduce((acc, item) => acc + item.quantity, 0);

  return (
    <CartContext
      value={{ cart, setCart, addToCart, removeFromCart, totalQuantity }}
    >
      {children}
    </CartContext>
  );
};
