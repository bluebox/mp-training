import React from 'react'

const EditBook = () => {
  
  return (
    <>
    {/* {editDialog && (
        <div className="fixed inset-0 bg-black bg-opacity-40 flex items-center justify-center z-50 p-4">
          <div className="bg-white w-full max-w-2xl rounded-lg shadow-lg p-6">
            <h2 className="text-2xl font-bold mb-4 text-gray-700">Edit Book</h2>
            <form onSubmit={handleEditSubmit} className="space-y-4">
              <div>
                <label className="block mb-1">Status</label>
                <select name="status" value={editData.status} onChange={handleEditChange} className="w-full border border-gray-300 rounded px-3 py-2">
                  <option value={true}>Active</option>
                  <option value={false}>Inactive</option>
                </select>
              </div>
              <div>
                <label className="block mb-1">Availability</label>
                <select name="availablity" value={editData.availablity} onChange={handleEditChange} className="w-full border border-gray-300 rounded px-3 py-2">
                  <option value={true}>Available</option>
                  <option value={false}>Unavailable</option>
                </select>
              </div>
              <div className="flex justify-end gap-2">
                <button type="button" onClick={() => setEditDialog(false)} className="px-4 py-2 bg-gray-300 rounded hover:bg-gray-400">Cancel</button>
                <button type="submit" className="px-4 py-2 bg-green-500 text-white rounded hover:bg-green-600">Update</button>
              </div>
            </form>
          </div>
        </div>
      )} */}
    </>
  )
}

export default EditBook
