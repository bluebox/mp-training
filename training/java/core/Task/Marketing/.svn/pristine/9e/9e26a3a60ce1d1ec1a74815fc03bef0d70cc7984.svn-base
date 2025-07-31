import React from 'react'
import { Button } from 'react-bootstrap'

const ViewRegions = ({ regions, props }) => {
    const storeCount = regions.filter(region => region.length === 12).length;
    return (
        <div className='my-3 '>
            <label className='custom-fieldset mb-2 col-2'>Regions - {regions.length}</label>
            {storeCount !== 0 &&
            <div className="mb-2">
            <span className="badge border border-secondary text-secondary rounded-5">
              Stores  - {storeCount}
            </span>
            </div>
            }

            <div className="d-flex flex-wrap gap-3" style={{ maxHeight: '200px', overflowY: 'auto' }}>
            {regions.map((value) => (
                <div key={value}>
                    <Button variant="light" className={`${props.className}  align-items-center btn btn-light btn-sm d-flex rounded-5 mb-2`}>
                        <span>{value}</span>
                    </Button>
                </div>
                )
            )}
            </div>
        </div>
    )
}

export default ViewRegions