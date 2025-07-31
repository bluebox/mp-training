import React from 'react';

const CombinationSelector = ({orCombinationId, andCombinationId,orCombinationValue,andCombinationValue, selectedValue, onChange, parameterResult }) => {
    
  return (
    <>
      {parameterResult?.length > 1 && (
        <div className="combinationGrp" id="combinationGrp">
          <label htmlFor="combinationGrp" className="text-secondary small">
            Select the Combination
          </label>
          <div className="combinationValue" id="combinationValue" name="combinationValue">
            <div className="form-check form-check-inline">
              <input
                name={orCombinationValue}
                type="radio"
                id={orCombinationId}
                className="form-check-input"
                value="or"
                onChange={onChange}
                checked={selectedValue === 'or'}
              />
              <label htmlFor={orCombinationId} className="form-check-label">OR</label>
            </div>
            <div className="form-check form-check-inline">
              <input
                name={andCombinationValue}
                type="radio"
                id={andCombinationId}
                className="form-check-input"
                value="and"
                onChange={onChange}
                checked={selectedValue === 'and'}
              />
              <label htmlFor={andCombinationId} className="form-check-label">AND</label>
            </div>
          </div>
        </div>
      )}
    </>
  );
};

export default CombinationSelector;
