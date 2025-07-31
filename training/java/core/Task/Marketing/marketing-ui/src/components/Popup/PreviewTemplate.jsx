import { CustomSpinners } from "@medplus/react-common-components/DynamicForm";
import React, { useContext, useEffect, useRef, useState } from "react";
import ResponseHandler from "../../helpers/ResponseHandler";
import Validate from "../../helpers/Validate";
import PopupService from "../../services/PopupService";
import { AlertContext } from "../Contexts/UserContext";
import { BodyComponent, Wrapper } from "../common/CommonStructure";

const PreviewTemplate = ({ requestId, templateId, setShowPreview, setStackedToastContent, ...props }) => {
  const validate = Validate();
  const popUpService = PopupService();
  const [template, setTemplate] = useState(null);
  const [isLoading, setLoading] = useState(false);

  useEffect(() => {
    if (validate.isNotEmpty(requestId)) {
      getTemplateByRequestId(requestId);
    } else if (validate.isNotEmpty(templateId)) {
      getTemplateByTemplateId(templateId)
    } else {
      setTemplate({ header: props.header, message: props.message, footer: props.footer, templateName: props.templateName });
    }
  }, [requestId, templateId]);

  const getTemplateByRequestId = async (requestId) => {
    try {
      setLoading(true);
      let response = await popUpService.getTemplateRequest({ requestId });
      setLoading(false);
      ResponseHandler(setStackedToastContent).handleResponse(
        response,
        { successAlert: true },
        handleSuccessResponse,
        (error) => {
          setStackedToastContent({ toastMessage: error });
          setShowPreview(false);
        }
      );
    } catch (error) {
      console.log(error);
      setStackedToastContent({toastMessage: "Unable to get template"});
      setShowPreview(false);
    }
  }

  const getTemplateByTemplateId = async (templateId) => {
    try {
      setLoading(true);
      let response = await popUpService.getTemplateInfo({ templateId });
      setLoading(false);
      ResponseHandler(setStackedToastContent).handleResponse(
        response,
        { successAlert: true },
        handleSuccessResponse,
        (error) => {
          setStackedToastContent({ toastMessage: error });
          setShowPreview(false);
        }
      );
    } catch (error) {
      console.log(error);
      setStackedToastContent({toastMessage:"Unable to get template"});
      setShowPreview(false);
    }

  }

  const handleSuccessResponse = (responseData) => {
    setTemplate(responseData);
  }
  const DIV_TAG = "<p><br></p>";
  return (
    <React.Fragment>
      {!isLoading ? (validate.isNotEmpty(template) &&
        <>
          <div className="row g-0">
            <div className="col-12">
              <div class="form-floating">
                <input aria-label="Template Name" type="text" readonly="" id="TemplateName" className="form-control-plaintext form-control-sm" value={template.templateName} />
                <label for="TemplateName">Template Name</label>
              </div>
            </div>
          </div>
          <div className="card ql-editor p-0 h-auto">
            {validate.isNotEmpty(template.header) && <div className="card-header bg-transparent">
              <div className="mb-0  p-0" dangerouslySetInnerHTML={{ __html: template.header }} />
            </div>}
            {validate.isNotEmpty(template.message) && DIV_TAG != template.message && <div className="card-body">
              <div dangerouslySetInnerHTML={{ __html: template.message }} />
            </div>}
            {validate.isNotEmpty(template.footer) && DIV_TAG != template.footer && <div className="card-footer  bg-transparent">
              <div className="mb-0 p-0" dangerouslySetInnerHTML={{ __html: template.footer }} />
            </div>}
          </div>
        </>) : <CustomSpinners outerClassName={"align-items-center d-flex custom-spinner flex-column"} innerClass={"custom-spinner-text-width"} animation="border" variant="brand" spinnerText='Please be patient while we prepare your data!' />}
    </React.Fragment>
  );
};

export default PreviewTemplate;
