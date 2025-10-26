import { createContext } from "react";

const initialCartContext = {
  cart: [],
  setCart: () => {},
  addToCart: () => {
    console.log("product added to cart");
  },
  removeFormCart: () => {},
  totalQuantity: 0,
};

export const CartContext = createContext();

export const CartProvider = ({ children }) => {
  return <CartContext value={initialCartContext}>{children}</CartContext>;
};
