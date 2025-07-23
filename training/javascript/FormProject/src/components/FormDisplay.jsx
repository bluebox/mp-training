import Record from "./Record"


export default function FormDisplay({num_rows}){
    const data=[{"name":"Kanishka","email":"kanishka.medplus@gmail.com", "mobile":736892374},
    {"name":"Uday","email":"kanishka.medplus@gmail.com", "mobile":736892374},
    {"name":"Aanand","email":"kanishka.medplus@gmail.com", "mobile":736892374},
    {"name":"Sai","email":"kanishka.medplus@gmail.com", "mobile":736892374},
    {"name":"Ravi","email":"kanishka.medplus@gmail.com", "mobile":736892374}]

    return(
    <>
    <h1>Display form</h1>
    <table>
        <thead>
        <tr>
            <th>Name</th>
            <th>Email</th>
            <th>Mobile</th>
        </tr>
        </thead>
        <tbody>
             {data.map((record)=>{return <Record key={record.name} record_obj={record}/>})}
        </tbody>
    </table>
    </>
);
}
