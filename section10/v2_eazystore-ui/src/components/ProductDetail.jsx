import React from "react";
import { useParams, useLocation } from "react-router-dom";

export default function ProductDetail() {
  const params = useParams();
  const location = useLocation();

  const product = location.state?.product; // this approach can be useful when you are not looking for new info on the current object

  return (
    <div>
      <h1>{product.name}</h1>
    </div>
  );
}
