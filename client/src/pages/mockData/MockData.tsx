import React, {useEffect, useState} from "react";
import {getMockData} from "../../services/mock.service";
import {Character} from "../../types/MockData";

function MockData() {
    const [mockData, setMockData] = useState([]);

    useEffect(() => {
        getData();
    }, []);

    const getData = () => {
        getMockData()
            .then((data) => {
                setMockData(data.characters);
            })
            .catch((error) => {
                console.log("error = ", error);
            });
    };

    return (
        <div className="container my-3 my-sm-5">
            <h1 className="mb-3 mb-sm-5 text-center">Mock data page</h1>

            <h3>Mock data character names are as follows:</h3>
            {mockData.map((character: Character, index: number) =>
                <div key={index}>{character.name}</div>
            )}
        </div>
    );
}

export default MockData;