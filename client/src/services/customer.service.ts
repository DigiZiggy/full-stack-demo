import axios from "axios";

const BASE_URL: string = process.env.BACKEND_URL as string;

export const getAllCustomers = async () => {
    return await axios.get(`${BASE_URL}/api/v1/customers/all`);
};

export const addCustomer = async (formData: FormData) => {
    return await axios.post(`${BASE_URL}/api/v1/customers/add`, formData);
};

export const deleteCustomer = async (id: number) => {
    return await axios.delete(`${BASE_URL}/api/v1/customers/delete/${id}`);
};

export const updateCustomer = async (formData: FormData, id: number) => {
    return await axios.put(
        `${BASE_URL}/api/v1/customers/update/${id}`,
        formData
    );
};