import React, { useState, useEffect } from 'react';
import crud from '../helper/crud';

function Table({ resource, fields, fieldLabels }) {
  const [items, setItems] = useState([]);
  const [editing, setEditing] = useState(null);
  const [form, setForm] = useState({});
  const keyField = fields[0]; // e.g. 'student_id'

  useEffect(() => {
    loadItems();
  }, []);

  async function loadItems() {
    const data = await crud.getAll(resource);
    setItems(data);
  }

  function onChange(e) {
    setForm({ ...form, [e.target.name]: e.target.value });
  }

  function startAdd() {
    setEditing(null);
    setForm({});
  }

  function startEdit(item) {
    setEditing(item[keyField]);
    setForm(item);
  }

  async function onSubmit(e) {
    e.preventDefault();
    if (editing) {
      await crud.update(resource, editing, form);
    } else {
      await crud.create(resource, form);
    }
    loadItems();
    setEditing(null);
    setForm({});
  }

  async function onDelete(id) {
    if (window.confirm('Confirm delete?')) {
      await crud.remove(resource, id);
      loadItems();
    }
  }

  return (
    <div style={{ padding: '1em', border: '1px solid #ccc', margin: '1em 0' }}>
      <h3>{resource}</h3>
      <button onClick={startAdd}>+ Add New</button>
      <table border="1" cellPadding="5">
        <thead>
          <tr>
            {fields.map(f => <th key={f}>{fieldLabels[f] || f}</th>)}
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {items.map(item =>
            <tr key={item[keyField]}>
              {fields.map(f => <td key={f}>{item[f]}</td>)}
              <td>
                <button onClick={() => startEdit(item)}>Edit</button>
                <button onClick={() => onDelete(item[keyField])}>Delete</button>
              </td>
            </tr>
          )}
        </tbody>
      </table>

      <h4>{editing ? 'Edit' : 'Add'} {resource}</h4>
      <form onSubmit={onSubmit}>
        {fields.filter(f => f !== keyField).map(f => (
          <div key={f}>
            <label>
              {fieldLabels[f] || f}:&nbsp;
              <input
                name={f}
                value={form[f] || ''}
                onChange={onChange}
              />
            </label>
          </div>
        ))}
        <button type="submit">Save</button>
        {editing && <button type="button" onClick={() => { setEditing(null); setForm({}); }}>Cancel</button>}
      </form>
    </div>
  );
}

export default Table;
