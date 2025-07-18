import { makeRequest } from "./AxiosTemplate";

export const createMappingRequest = async ({
  categoryId,
  productId,
  requestedBy,
}) => {
  return makeRequest(
    "POST",
    "/api/category-product-mapping-requests/create",
    {},
    {},
    {
      categoryId: parseInt(categoryId),
      productId: parseInt(productId),
      requestedBy: parseInt(requestedBy),
    }
  );
};

export const getMappingRequests = async (requestIds, status, loggedInUser) => {
  const params = {};

  if (requestIds && requestIds.length > 0) {
    params.requestIds = requestIds;
  }
  if (status !== '') {
    params.status = status;
  }
  if (loggedInUser !== null && !isNaN(loggedInUser)) {
    params.loggedInUser = loggedInUser;
  }

  return makeRequest(
    "GET",
    "api/category-product-mapping-requests/search",
    {},
    params,
    {}
  );
};

export const updateMappingRequestStatus = async (requestIds, approvedBy, status) => {
  return makeRequest(
    "POST",
    "/api/category-product-mapping-requests/update-status",
    {},
    {},
    {
      requestIds: requestIds,
      approvedBy: approvedBy,
      status: status,
    }
  );
};
