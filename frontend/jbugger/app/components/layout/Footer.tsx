// components/layout/Footer.tsx
import React from "react";

const Footer = () => {
  return (
    <footer className="bg-msg-magenta py-4 fixed bottom-0 w-full">
      <div className="container mx-auto flex justify-between items-center">
        <div className="flex-shrink-0">
          <div className=" text-white p-2 rounded">
            2023 .msg Systems Romania
          </div>
        </div>
        <div className="flex items-center">
          <a
            href="https://example.com/politica-de-confidentialitate"
            target="_blank"
            rel="noopener noreferrer"
            className="mr-4 text-gray-700 hover:text-gray-900"
          >
            Politica de confidentialitate
          </a>
          <p className="text-gray-700">Tel: 0707070707</p>
        </div>
      </div>
    </footer>
  );
};

export default Footer;
