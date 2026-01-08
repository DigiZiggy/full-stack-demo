import React from "react"
import Header from "./layouts/header/Header";
import {Route, BrowserRouter as Router, Routes} from "react-router-dom";
import Home from "./pages/home/Home";
import Footer from "./layouts/footer/Footer";
import About from "./pages/about/About";
import AddCustomer from "./pages/addCustomer/AddCustomer";
import Customers from "./pages/customers/Customers";
import MockData from "./pages/mockData/MockData";

function App() {
    return (
        <Router>
            <Header />
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/about" element={<About />} />
                <Route path="/add-customer" element={<AddCustomer />} />
                <Route path="/customers" element={<Customers />} />
                <Route path="/mock-data" element={<MockData />} />
            </Routes>
            <Footer />
        </Router>
    );
}

export default App