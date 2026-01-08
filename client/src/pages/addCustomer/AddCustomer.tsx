import React, { useState } from "react";
import { useNavigate } from "react-router";
import {addCustomer} from "../../services/customer.service";

function AddCustomer() {
    const navigate = useNavigate();

    const [formData, setFormData] = useState({
        customerId: "",
        firstName: "",
        lastName: "",
        email: "",
        mobile: "",
        address: "",
        password: "",
        picture: "",
        pictureUrl: ""
    });

    const [customerPictureFile, setCustomerPictureFile] = useState<File | null>(null);
    const [customerPicturePreview, setCustomerPicturePreview] = useState<string>("");

    const handleFormDataChange = (
        e: React.ChangeEvent<HTMLInputElement> | React.ChangeEvent<HTMLTextAreaElement>
    ) => {
        setFormData((prevState) => ({
            ...prevState,
            [e.target.name]: e.target.value,
        }));
    };

    const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        if (e.target.files && e.target.files[0]) {
            const file = e.target.files[0];
            setCustomerPictureFile(file);

            const reader = new FileReader();
            reader.onloadend = () => {
                setCustomerPicturePreview(reader.result as string);
            };
            reader.readAsDataURL(file);
        }
    };

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();

        const submitData = new FormData();

        submitData.append("customerDtoJson", JSON.stringify(formData));

        if (customerPictureFile) {
            submitData.append("file", customerPictureFile);
        }

        addCustomer(submitData)
            .then((response) => {
                console.log("Add customer response = ", response);
                handleReset();
                navigate("/");
            })
            .catch((error) => {
                console.log("Error adding customer : ", error);
            });
    };

    const handleReset = () => {
        setFormData({
            customerId: "",
            firstName: "",
            lastName: "",
            email: "",
            mobile: "",
            address: "",
            password: "",
            picture: "",
            pictureUrl: ""
        });

        setCustomerPictureFile(null);
        setCustomerPicturePreview("");
    };

    return (
        <div className="container my-3 my-sm-5">
            <h1 className="mb-3 mb-sm-5 text-center">Add New Customer</h1>

            <form onSubmit={handleSubmit}>
                <div className="mb-3">
                    <label htmlFor="firstName" className="form-label">Firstname</label>
                    <input type="text" value={formData.firstName} onChange={handleFormDataChange}
                           className="form-control" id="firstName" name="firstName"/>
                </div>
                <div className="mb-3">
                    <label htmlFor="lastName" className="form-label">Lastname</label>
                    <input type="text" value={formData.lastName} onChange={handleFormDataChange}
                           className="form-control" id="lastName" name="lastName"/>
                </div>
                <div className="mb-3">
                    <label htmlFor="email" className="form-label">Email address</label>
                    <input type="email" value={formData.email} className="form-control"
                           id="email" name="email"
                           onChange={handleFormDataChange} placeholder="name@example.com"/>
                </div>
                <div className="mb-3">
                    <label htmlFor="mobile" className="form-label">Phone</label>
                    <input type="text" value={formData.mobile} className="form-control"
                           id="mobile" name="mobile"
                           onChange={handleFormDataChange}/>
                </div>
                <div className="mb-3">
                    <label htmlFor="password" className="form-label">Password</label>
                    <input type="text" value={formData.password} className="form-control"
                           id="password" name="password"
                           onChange={handleFormDataChange}/>
                </div>
                <div className="mb-3">
                    <label htmlFor="address" className="form-label">Address</label>
                    <textarea className="form-control" value={formData.address}
                              id="address" name="address"
                              onChange={handleFormDataChange} rows={3}></textarea>
                </div>

                <div className="mb-3">
                    <label htmlFor="picture" className="form-label">Profile picture</label>
                    <input className="form-control" type="file"
                           id="picture" name="picture"
                           onChange={handleFileChange}/>
                </div>

                <button className="btn btn-primary" type="submit">Submit</button>
            </form>
        </div>
    );
}

export default AddCustomer;