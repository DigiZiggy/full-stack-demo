import React, { useEffect, useState } from "react";
import {deleteCustomer, getAllCustomers} from "../../services/customer.service";
import {Customer} from "../../types/Customer";

function Customers() {
    const [customers, setCustomers] = useState<Customer[]>([]);

    console.log("Customers page render");
    useEffect(() => {
        console.log("Customers page useEffect");
        getCustomers();
    }, []);

    const getCustomers = () => {
        getAllCustomers()
            .then((response) => {
                console.log("response = ", response.data);
                setCustomers(response.data);
            })
            .catch((error) => {
                console.log("error = ", error);
            });
    };

    const handleDelete = (customer: Customer) => {
        deleteCustomer(customer.customerId)
            .then((response) => {
                console.log("response = ", response.data);
                getCustomers();
            })
            .catch((error) => {
                console.log("error = ", error);
            });
    };

    return (
        <div className="container my-3 my-sm-5">
            <h1 className="mb-3 mb-sm-5 text-center">List of Customers</h1>
            <div className="row">
                {customers.map((customer) => (
                    <div key={customer.customerId} className="col-12 col-sm-6 col-md-4 col-lg-3 col-xl-2 mx-5 my-5 text-center">
                        <img src={customer.pictureUrl} className="img-fluid rounded-circle mb-1"
                             alt="Customer"/>
                        <ul className="list-inline mb-1">
                            <li className="list-inline-item small"><a href="#">Twitter</a></li>
                            <li className="list-inline-item small"><a href="#">LinkedIn</a></li>
                        </ul>
                        <h5>{customer.firstName} {customer.lastName}</h5>
                        <p>Email: {customer.email}</p>
                        <p>Phone: {customer.mobile}</p>

                        <button type="button" className="btn btn-primary">
                            Edit
                        </button>
                        <button type="button" className="btn btn-danger"
                                onClick={() => handleDelete(customer)}>
                            Delete
                        </button>
                    </div>
                ))}
            </div>
        </div>
    );
}

export default Customers;