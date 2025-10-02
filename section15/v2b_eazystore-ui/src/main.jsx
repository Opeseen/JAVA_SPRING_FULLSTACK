import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import {
  createBrowserRouter,
  RouterProvider,
  createRoutesFromElements,
  Route,
} from "react-router-dom";
import { ToastContainer, Bounce } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import "./index.css";
import App from "./App.jsx";
import About from "./components/About.jsx";
import Contact, { contactAction } from "./components/Contact.jsx";
import Cart from "./components/Cart.jsx";
import Login from "./components/Login.jsx";
import Home, { productsLoader } from "./components/Home.jsx";
import ErrorPage from "./components/ErrorPage.jsx";
import CheckoutForm from "./components/CheckoutForm.jsx";
import ProductDetail from "./components/ProductDetail.jsx";
import ProtectedRoute from "./components/ProtectedRoute.jsx";
import { CartProvider } from "./store/CartContext.jsx";
import { AuthProvider } from "./store/AuthContext.jsx";
import { loginAction, registerAction } from "./services/AuthAction.js";
import Profile, {
  profileAction,
  profileLoader,
} from "./components/Profile.jsx";
import Orders from "./components/Orders.jsx";
import AdminOrders from "./components/admin/AdminOrders.jsx";
import Messages from "./components/admin/Messages.jsx";
import Register from "./components/Register.jsx";

const routeDefinitions = createRoutesFromElements(
  <Route path="/" element={<App />} errorElement={<ErrorPage />}>
    <Route index element={<Home />} loader={productsLoader} />
    <Route path="/home" element={<Home />} loader={productsLoader} />
    <Route path="/about" element={<About />} />
    <Route path="/contact" element={<Contact />} action={contactAction} />
    <Route path="/login" element={<Login />} action={loginAction} />
    <Route path="/register" element={<Register />} action={registerAction} />
    <Route path="/cart" element={<Cart />} />
    <Route path="/products/:productId" element={<ProductDetail />} />
    <Route element={<ProtectedRoute />}>
      <Route path="/checkout" element={<CheckoutForm />} />
      <Route
        path="/profile"
        element={<Profile />}
        loader={profileLoader}
        action={profileAction}
        shouldRevalidate={({ actionResult }) => {
          return !actionResult?.success;
        }}
      />
      <Route path="/orders" element={<Orders />} />
      <Route path="/admin/orders" element={<AdminOrders />} />
      <Route path="/admin/messages" element={<Messages />} />
    </Route>
  </Route>
);

const appRouter = createBrowserRouter(routeDefinitions);

createRoot(document.getElementById("root")).render(
  <StrictMode>
    <AuthProvider>
      <CartProvider>
        <RouterProvider router={appRouter} />
      </CartProvider>
    </AuthProvider>

    <ToastContainer
      position="top-center"
      autoClose={3000}
      hideProgressBar={false}
      newestOnTop={false}
      draggable
      pauseOnHover
      theme={localStorage.getItem("theme") === "dark" ? "dark" : "light"}
      transition={Bounce}
    />
  </StrictMode>
);
