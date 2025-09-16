import React, { useEffect } from "react";
import { Navigate, Outlet, useLocation } from "react-router-dom";
import { useAuth } from "../store/AuthContext";

export default function ProtectedRoute() {
  const { isAuthenticated } = useAuth();
  const location = useLocation();

  // useEffect to navigate the user to the protected route they were going to b4 they were logged in
  useEffect(() => {
    // check if they were not authenticated b4 and the route they wanna access b4 was not the logged in route
    if (!isAuthenticated && location.pathname !== "/login") {
      // store to the session storage so the info will be cleared when the user closes the browser
      sessionStorage.setItem("redirectPath", location.pathname);
    }
  }, [isAuthenticated, location.pathname]);
  return isAuthenticated ? <Outlet /> : <Navigate to="/login" />;
}
