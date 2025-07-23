export default function Record({record_obj})
{
        function obj_to_fielddata(record_obj){
            let res=[]
            for(let key in record_obj){
                res.push(<td key={key}>{record_obj[key]}</td>)
            }
            return res
            }
    return(
    <tr>
        {
            obj_to_fielddata(record_obj)
        }
    </tr>
    );
}