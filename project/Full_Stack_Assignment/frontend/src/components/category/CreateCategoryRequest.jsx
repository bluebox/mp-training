import React from 'react';
import CreateCategoryRequestForm from './CreateCategoryRequestForm'; // ✅ default import

const CreateCategoryRequest = () => {
  return (
    <div>
      <p>Create a new category request</p>
      <CreateCategoryRequestForm />
    </div>
  );
};

export default CreateCategoryRequest;
