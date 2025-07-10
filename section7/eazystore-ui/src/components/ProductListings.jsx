import React from 'react'
import ProductCard from './ProductCard'

export default function ProductListings({products}) {
  return (
    <div className='max-w-[1152px] mx-auto'>
      <div className='grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-y-8 gap-x-6 py-12'>
        {/* using ternary operator */}
        {products.length > 0 ? (
          products.map((product) => (
            // parent (PL) passes product to child (PC)
            <ProductCard key={product.productId} product={product} />  
          ))
        ) : (
          <p className='font-primary font-bold text-lg text-primary text-center'>No product found</p>
        )}
      </div>
    </div>
  )
};
