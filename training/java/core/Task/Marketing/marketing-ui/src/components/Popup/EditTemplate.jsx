import CreateTemplate from "./CreateTemplate";

const EditTemplate=(props)=>{

    return(
        <CreateTemplate isEditTemplate={true} {...props}/>
    )
}
export default EditTemplate;