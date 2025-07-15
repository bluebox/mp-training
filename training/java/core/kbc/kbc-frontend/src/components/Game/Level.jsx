function Level({ handleLevel }) {
    return (
        <>
            <h3>Choose the Difficulty Level: </h3>
            <button className="button-m" onClick={() => handleLevel("Easy")}>Easy</button><br />
            <button className="button-m" onClick={() => handleLevel("Medium")}>Medium</button><br />
            <button className="button-m" onClick={() => handleLevel("Hard")}>Hard</button><br />

        </>

    );
}
export default Level;