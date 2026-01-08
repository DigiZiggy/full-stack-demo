export const getMockData = async () => {
    return await fetch('/mock-data.json')
        .then((response) => {
            return response.json();
        });
};