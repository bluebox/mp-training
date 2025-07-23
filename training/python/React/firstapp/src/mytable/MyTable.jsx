
function TableRow({ id, name, age }) {
  return (
    <tr>
      <td>{id}</td>
      <td>{name}</td>
      <td>{age}</td>
    </tr>
  );
}
function MyTable() {
  return (
    <table style={{ border: '1px solid black' }}>
      <thead>
        <TableRow id={"id"} name={"Name"} age={"Age"} />
      </thead>
      <tbody>
        <TableRow id={1} name={"Jane Smith"} age={25} />
        <TableRow id={2} name={"Alice Johnson"} age={28} />
      </tbody>
    </table>
  );
}

export default MyTable;
