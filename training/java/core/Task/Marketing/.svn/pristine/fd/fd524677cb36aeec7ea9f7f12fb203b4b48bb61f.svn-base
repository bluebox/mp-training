import DynamicForm, { CustomSpinners, withFormHoc } from "@medplus/react-common-components/DynamicForm";
import React, { useCallback, useContext, useEffect, useMemo, useRef, useState } from "react";
import { Button, Modal } from "react-bootstrap";
import ReactQuill, { Quill } from "react-quill";
import { UncontrolledTooltip } from "reactstrap";
import { POPUP_FORM_URLS, POPUP_URLS } from "../../constants/UrlConstants";
import ResponseHandler from "../../helpers/ResponseHandler";
import Validate from "../../helpers/Validate";
import CancelIcon from "../../images/cross.svg";
import BackButton from "../../images/leftarrow_black_icon_18px.svg";
import PopupService from "../../services/PopupService";
import { REQUEST_TYPE } from "../../services/ServiceConstants";
import { AlertContext } from "../Contexts/UserContext";
import { BodyComponent, FooterComponent, HeaderComponent, Wrapper } from "../common/CommonStructure";
import InfoIcon from "../common/InfoIcon";
import PreviewTemplate from "./PreviewTemplate";
import KeyboardShortcuts from "../../helpers/KeyboardShortcuts";
import { POPUP_ROLES } from "../../constants/MarketingRoles";
import { PATHLABS, PHARMACY } from "../../constants/MarketingConstant";

const DIV_TAG = "<p><br></p>";
const MAX_IMAGE_WIDTH = 570;
const MAX_IMAGE_HEIGHT = 300;
const MAX_TEXT_LENGTH = 400;
const MAX_IMAGE_SIZE = 150000;

const CreateTemplate = ({ helpers, isEditTemplate = false, ...props }) => {

  var image = Quill.import("formats/image");
  Quill.register(image, true);

  const [tempHeader, setTempHeader] = useState("");
  const [tempMsg, setTempMsg] = useState("");
  const [tempFooter, setTempFooter] = useState("");
  const headerRef = useRef(null);
  const footerRef = useRef(null);
  const validate = Validate();
  const popupService = PopupService();
  const { setStackedToastContent } = useContext(AlertContext);
  const quillRef = useRef();
  const [imagePath, setImagePath] = useState("");
  const [isShowPreview, setShowPreview] = useState(false);
  const [isLoading, setLoading] = useState(false);
  const [id, setId] = useState(undefined);
  const [isDisable, setDisable] = useState(true);
  const [imageWidth, setImageWidth] = useState(MAX_IMAGE_WIDTH);
  const [imageHeight, setImageHeight] = useState(MAX_IMAGE_HEIGHT);
  const [isNoteModalOpen, setNoteModalOpen] = useState(false);
  const [wrapperKey, setWrapperKey] = useState(1);
  const isCloneTemplate = props.location.state?.isCloneTemplate;
  const HAS_POPUP_LAB_TEMPLATE_CREATION_ROLE = validate.validateRole(POPUP_ROLES.createLabTemplate);
    const HAS_POPUP_PHARMA_TEMPLATE_CREATION_ROLE = validate.validateRole(POPUP_ROLES.createPharmaTemplate);
  const [initialData, setInitialData] = useState({})

  useEffect(() => {
    setWrapperKey(wrapperKey + 1);
  }, [props.path])

  useEffect(()=>{
    let id = props.location.state?.templateInfo.id || props.location.state?.templateInfo.requestId;
    if(id){
      prepareTemplateData(id);
    }
  },[])

  const prepareTemplateData = async (id) => {
    await getTemplateById(id, validate.isNotEmpty(props.location.state?.templateInfo.requestId));
  }

  const setInitialDataForLoading = (templateInfo) =>{
    setInitialData({...templateInfo, name: isCloneTemplate ?  "" : templateInfo.templateName, applicableType: getApplicableType(templateInfo?.applicableType)})
  }

  const getTemplateById= async (id,isTemplateRequest) =>{
    const response = isTemplateRequest ? await popupService.getTemplateRequest({'requestId':id}).catch((error)=>{setToastMessageForError(error)}) : await popupService.getTemplateInfo({ 'templateId': id }).catch((error)=>{setToastMessageForError(error)});
    ResponseHandler(setStackedToastContent).handleResponse(response, {}, (data) => {setTemplateInfo(data); setInitialDataForLoading(data)}, (error) => setStackedToastContent({ toastMessage: error }));
  }

  const setToastMessageForError = (error,message) => {
    console.log(error);
    setStackedToastContent({ toastMessage: message ? message : "Unable to get template, please try again." })
  }

  const getApplicableType = (applicableType) => {
    if(validate.isEmpty(applicableType)){
      return "";
    }
    if("Path Labs" == applicableType || "PATHLABS" == applicableType){
      return "PATHLABS";
    }
    else if("Pharmacy" == applicableType || "PHARMACY" == applicableType){
      return "PHARMACY";
    }
  }

  const setTemplateInfo = async (templateInfo) => {
    let isClone = validate.isNotEmpty(templateInfo?.templateId);
    if (validate.isEmpty(templateInfo) || (!isClone && "/marketing/ui/create-template" == props.path)) {
      reset();
      return;
    }
    helpers.updateSpecificValues({...templateInfo, name: isCloneTemplate ? "" : templateInfo.templateName, applicableType: getApplicableType(templateInfo?.applicableType) }, "createTemplateForm");
    setId(templateInfo.requestId);
    setTempHeader(templateInfo.header);
    setTempFooter(templateInfo.footer);
    setTempMsg(templateInfo.message);
    setImagePath(templateInfo.imagePath);
    if(!isClone){
      helpers.disableElement("reset");
      helpers.disableElement("applicableType");
    }
    validateForm({ 'header': templateInfo.header, 'message': templateInfo.message });
    const doc = (new DOMParser()).parseFromString(templateInfo.message, 'text/html');
    const imgElement = doc.querySelector('img');
    if (imgElement) {
      setImageHeight(imgElement.height);
      setImageWidth(imgElement.width);
    }
  };

  const reset = () => {
    setTempFooter("");
    setTempHeader("");
    setTempMsg("");
    helpers.resetForm("createTemplateForm", false, true, true, true);
    helpers.enableElement("name");
    helpers.enableElement("reset");
    setDisable(true);
    setShowPreview(false);
    helpers.showElement('createTemplateForm')
  };

  const toggle = () => {
    !isShowPreview ? helpers.hideElement('createTemplateForm') : helpers.showElement('createTemplateForm');
    setShowPreview(!isShowPreview);
  };

  const checkTemplateName = async (tempName) => {
    if (validate.isNotEmpty(tempName)) {
      let formData = { templateName: tempName };
      let response = await popupService.checkTemplateName(formData).catch((err) => {
        setStackedToastContent({ toastMessage: "Unable to check template name, please try again.", });
      });
      return ResponseHandler(setStackedToastContent).handleResponse(response, {}, () => { },
        (error) => {
          console.log(error);
          if (error.includes("already")){
            helpers.updateErrorMessage(error, "name");
          }
        }
      );
    }
  };

  const observers = {
    name: [['blur', () => checkTemplateName(validate.isNotEmpty(helpers.getHtmlElementValue("name")) ? helpers.getHtmlElementValue("name").trim() : "")],
    ['change', () => validateForm({ 'header': tempHeader, 'message': tempMsg })]],
    applicableType: [['change', () => validateForm({ 'header': tempHeader, 'message': tempMsg})]]
  };

  const imageHandler = useCallback(() => {
    const input = document.createElement("input");
    input.type = "file";
    input.accept = "image/png";
    input.click();
    input.onchange = (event) => {
      const file = event.target.files[0];
      if (!file) {
        return;
      }
      if (!file.type.match("image/png")) {
        setStackedToastContent({ toastMessage: "Please insert only .png format images.", });
        return;
      }
      if (validate.isNotEmpty(quillRef.current.getEditor().root.querySelectorAll("img"))) {
        setStackedToastContent({ toastMessage: "Only one image allowed.", });
        return;
      }
      if (file.size > MAX_IMAGE_SIZE) {
        setStackedToastContent({ toastMessage: "Please insert image less than 150 KB." });
        return;
      }
      handleUploadImage(file);
    };
  });

  const handleUploadImage = async (image) => {
    const editor = quillRef.current?.getEditor();
    const img = new Image();
    img.src = URL.createObjectURL(image);
    checkImageDimensions(img).then(async () => {
      try {
        let data = await popupService.uploadImage(image, "F");
        if (validate.isNotEmpty(data) && validate.isNotEmpty(data.imageServerUrl)) {
          const imageUrl = data.imageServerUrl + "/" + data[0].imagePath;
          setImagePath(imageUrl);
          setTimeout(() => {
            editor.insertEmbed(
              editor.getSelection()?.index,
              'image',
              imageUrl
            );
          }, 2000);
        }
      } catch (error) {
        console.log(error);
        return Promise.reject(error);
      }
    }).catch((errorMessage) => {
        setStackedToastContent({ toastMessage: errorMessage });
        setLoading(false);
      });
  };

  const checkImageDimensions = (image) => {
    return new Promise((resolve, reject) => {
      image.onload = () => {
        setImageHeight(image.height);
        setImageWidth(image.width);
        if (image.width > MAX_IMAGE_WIDTH) {
          setImageWidth(MAX_IMAGE_WIDTH);
        }
        if (image.height > MAX_IMAGE_HEIGHT) {
          setImageHeight(MAX_IMAGE_HEIGHT);
        }
        resolve();
      };
      image.onerror = () => {
        reject("Error during file upload.");
      };
    });
  };

  const formats = [
    "bold",
    "italic",
    "underline",
    "strike",
    "font",
    "size",
    "color",
    "outdent",
    "indent",
    "list",
    "bullet",
    "blockquote",
    "align",
    "background"
  ];

  const allFormats = [...formats, "image"];

  const modules = useMemo(
    () => ({
      toolbar: {
        container: [
          [{ size: [] }],
          ["bold", "italic", "underline", "blockquote", "strike"],
          [{ color: [] }, { background: [] }],
          [{ font: [] }],
          [{ align: [] }],
          [
            { list: "ordered" },
            { list: "bullet" },
            { indent: "-1" },
            { indent: "+1" },
          ],
          ["clean"],
        ],
      },
      clipboard: {
        matchVisual: true,
      },
    }),
    []
  );

  const allModules = useMemo(
    () => ({
      toolbar: {
        container: [
          [{ size: [] }],
          ["bold", "italic", "underline", "blockquote", "strike"],
          [{ color: [] }, { background: [] }],
          [{ font: [] }],
          [{ align: [] }],
          [
            { list: "ordered" },
            { list: "bullet" },
            { indent: "-1" },
            { indent: "+1" },
          ],
          ["image"],
          ["clean"],
        ],
        handlers: {
          image: imageHandler,
        },
      },
      clipboard: {
        matchVisual: true,
      },
    }),
    []
  );


  const validateApplicableType = () =>{
    const applicableType = helpers.getHtmlElementValue("applicableType");
    
    if(validate.isNotEmpty(applicableType)){
      return false;
    }
    if( HAS_POPUP_LAB_TEMPLATE_CREATION_ROLE && HAS_POPUP_PHARMA_TEMPLATE_CREATION_ROLE){
      return true;
    }else {
      return false;
    }
  }

  const validateForm = (param) => {
    setDisable(validateApplicableType() ||  validate.isEmpty(helpers.getHtmlElementValue('name')) || ((validate.isEmpty(param.header) || DIV_TAG === param.header) && (validate.isEmpty(param.message) || DIV_TAG === param.message)));
  }

  const getChangedImageDimensionsTempMsg = (html) => {
    const doc = (new DOMParser()).parseFromString(html, 'text/html');
    const imgElement = doc.querySelector('img');
    if (imgElement) {
      imgElement.setAttribute('width', imageWidth);
      imgElement.setAttribute('height', imageHeight);
    }
    return doc.body.innerHTML;
  }

  const customHtmlMap = {
    header: [["INSERT_IN", () => {
      return (
        <>
          <ReactQuill
            value={tempHeader}
            formats={formats}
            modules={modules}
            onChange={(html) => {
              setTempHeader(html);
              validateForm({ 'header': html, 'message': tempMsg });
            }}
            placeholder="Please enter header"
          />
        </>
      );
    },],],
    message: [["INSERT_IN", () => {
      return (
        <>
          <ReactQuill
            ref={(el) => { quillRef.current = el; }}
            formats={allFormats}
            modules={allModules}
            placeholder="Please enter message/insert image "
            onChange={(html) => {
              setTempMsg(html);
              validateForm({ 'message': html, 'header': tempHeader });
            }}
            value={tempMsg}
          />
        </>
      );
    },],],
    footer: [["INSERT_IN", () => {
      return (
        <>
          <ReactQuill
            value={tempFooter}
            formats={formats}
            modules={modules}
            onChange={(payload) => setTempFooter(payload)}
            placeholder="Please enter footer"
          />
        </>
      );
    },],],
  };

  const createTemplateRequest = async (templateInfo) => {
    let response = await popupService.createPopupTemplateRequest(templateInfo).catch((error) => setStackedToastContent({ toastMessage: "Unable to create popup template, please try again" }));
    setLoading(false);
    ResponseHandler(setStackedToastContent).handleResponse(response, { successAlert: true },
      (data) => {
        setStackedToastContent({ toastMessage: `Popup Template Request ${templateInfo.templateName} created successfully.`, });
        successCallBackHandler();
      },
      (error) => {
        setStackedToastContent({ toastMessage: error });
      }
    );
  };

  const successCallBackHandler = () => {
    props.history.push({
      pathname: POPUP_URLS.viewTemplateRequests,
      state: {
        searchCriteria: !isCloneTemplate ? props.location.state?.searchCriteria : null,
        id:id,
        isFromEdit : isEditTemplate,
        isCloneTemplate : isCloneTemplate
      },
    });
  };

  const updateTemplateRequest= async (templateInfo) =>{
    const response = await popupService.updatePopupTemplateRequest(templateInfo).catch(() => {setStackedToastContent({ toastMessage : "Unable to update template request, please try again."})});
    ResponseHandler(setStackedToastContent).handleResponse(response, {}, ()=>{setStackedToastContent({ toastMessage: `Popup Template Request ${templateInfo.templateName} updated successfully.`}); successCallBackHandler();}, (error)=>{setStackedToastContent({ toastMessage: error });setLoading(false);});
  }

  const handleSubmitAction = async () => {
    helpers.validateAndSetErrorMessagesInForm('createTemplateForm');
    let tempName = helpers.getHtmlElementValue("name");
    let applicableType = helpers.getHtmlElementValue('applicableType');

    if(validate.isEmpty(applicableType) && HAS_POPUP_LAB_TEMPLATE_CREATION_ROLE && HAS_POPUP_PHARMA_TEMPLATE_CREATION_ROLE){
      setStackedToastContent({ toastMessage: 'Please select applicable Type.'});
      return;
    }

    if (validate.isNotEmpty(tempName)) {
      let errorsArr = [];
      if (tempHeader?.length > MAX_TEXT_LENGTH) {
        errorsArr.push("Header");
      }
      if (tempMsg?.length > MAX_TEXT_LENGTH) {
        errorsArr.push("Body");
      }
      if (tempFooter?.length > MAX_TEXT_LENGTH) {
        errorsArr.push("Footer");
      }
      if (validate.isNotEmpty(errorsArr)) {
        setStackedToastContent({ toastMessage: `Only ${MAX_TEXT_LENGTH} characters allowed in ${errorsArr.join(", ")}` });
        return;
      }
      setLoading(true);
      let templateInfo = {};

      if (validate.isNotEmpty(applicableType)) {
        templateInfo['applicableType'] = applicableType;
      } else {
        if (HAS_POPUP_LAB_TEMPLATE_CREATION_ROLE) {
          templateInfo['applicableType'] = PATHLABS;
        } else if (HAS_POPUP_PHARMA_TEMPLATE_CREATION_ROLE) {
          templateInfo['applicableType'] = PHARMACY;
        }
      }

      templateInfo["templateName"] = tempName;
      templateInfo["header"] = tempHeader;
      templateInfo["message"] = getChangedImageDimensionsTempMsg(tempMsg);
      templateInfo["footer"] = tempFooter;
      templateInfo["imagePath"] = imagePath;
      isEditTemplate ? updateTemplateRequest({...templateInfo,requestId:id}) : createTemplateRequest(templateInfo);
    }
  };

  const goBack = () => {
    props.history.push({
      pathname: isEditTemplate ? POPUP_URLS.viewTemplateRequests : POPUP_URLS.viewTemplates,
      state: {
        searchCriteria: props.location.state?.searchCriteria,
        id:id,
        isBackClicked: true
      },
    });
  }

  const getNotesInfo = (height) => {
    return (
      <div class="customdropdown-position position-absolute text-secondary bottom " style={{ bottom: `${height}px` }}>
        <h6 class="text-dark font-14">Notes Information</h6>
        <ol>
          <li>Only .png format images are allowed.</li>
          <li>Image size should be less than 150KB.</li>
        </ol>
      </div>
    )
  }

  const resetCloneData = () =>{
    helpers.updateSpecificValues(initialData, "createTemplateForm");
    let isClone = validate.isNotEmpty(initialData?.templateId)
    setId(initialData.requestId);
    setTempHeader(initialData.header);
    setTempFooter(initialData.footer);
    setTempMsg(initialData.message);
    setImagePath(initialData.imagePath);
    if(!isClone){
      helpers.disableElement("reset");
      helpers.disableElement("applicableType");
    }
    validateForm({ 'header': initialData.header, 'message': initialData.message });
    const doc = (new DOMParser()).parseFromString(initialData.message, 'text/html');
    const imgElement = doc.querySelector('img');
    if (imgElement) {
      setImageHeight(imgElement.height);
      setImageWidth(imgElement.width);
    }
  }

  return (
    <>
      <Wrapper key={wrapperKey}>
        <HeaderComponent className={`border-bottom py-2 d-flex align-items-center justify-content-between ${!isShowPreview && (isEditTemplate || isCloneTemplate) ? "px-1" : "px-3"}`} ref={headerRef}>
          <div className="d-flex align-items-center">
            {!isShowPreview  && (isEditTemplate || isCloneTemplate) &&
              <>
                <Button id="back-button" variant="" className="btn-link icon-hover me-2" onClick={() => goBack()}>
                  <img aria-label="Close" src={BackButton} alt="Back Icon" />
                </Button>
                <UncontrolledTooltip placement="bottom" target='back-button'>
                  Back
                </UncontrolledTooltip>
              </>
            }
            {isShowPreview ? 'Preview Template' : isEditTemplate ? 'Edit Popup Template' :'Create Popup Template '}
          </div>
          {isShowPreview ?
            <div>
              <KeyboardShortcuts buttonId="template-close-button" handleEsc setOpenModal={() => toggle()}/>
              <Button id="template-close-button" color="link" variant=" " onClick={() => toggle()} className="rounded-5 btn-link icon-hover btn">
              <span class="custom-close-btn icon-hover"></span>
              </Button>
              <UncontrolledTooltip placement="bottom" target="template-close-button">
                Close
              </UncontrolledTooltip>
            </div>
            :
            <Button disabled={isDisable} variant="light" className="px-4 brand-secondary btn-sm" onClick={() => validate.isEmpty(helpers.getHtmlElement('name').message) && toggle()}>
              Preview
            </Button>
          }
        </HeaderComponent>
        <BodyComponent className="body-height" allRefs={{ headerRef, footerRef }}>
          {isShowPreview && <PreviewTemplate header={tempHeader} footer={tempFooter} message={tempMsg} templateName={helpers.getHtmlElementValue('name')} setStackedToastContent={setStackedToastContent}/>}
          <div>
            <DynamicForm
              requestUrl={POPUP_FORM_URLS.templateCreateForm}
              customHtml={customHtmlMap}
              requestMethod={REQUEST_TYPE.GET}
              helpers={helpers}
              observers={observers}
            />
          </div>
        </BodyComponent>
        <FooterComponent ref={footerRef} className='border-top p-2 d-flex justify-content-between'>
          {isNoteModalOpen && getNotesInfo(footerRef?.current?.offsetHeight)}
          <div>
            <button disabled={isShowPreview} type="button" className="btn btn-link link-dark font-12" onClick={() => { setNoteModalOpen(!isNoteModalOpen) }}>
              Note  <InfoIcon aria-hidden="true" modalOpen={!isNoteModalOpen} />
            </button>
          </div>
          <div>
            {!isEditTemplate && !isShowPreview && <button disabled={isLoading} className='px-4 btn btn-sm brand-secondary me-2' onClick={() => isCloneTemplate ? resetCloneData() : reset()}>
            {(isCloneTemplate) ? "Reset" : "Clear" }
            </button>}
            <Button disabled={isDisable || isLoading} className={`px-4 btn-sm me-2 btn ${isEditTemplate ? "btn-primary" : "btn-brand"} `} onClick={() => handleSubmitAction()}>
              {isLoading ? <CustomSpinners className={"spinner-position"} spinnerText={isEditTemplate ? 'Update' : isCloneTemplate ? 'Clone' : 'Submit'} innerClass={"invisible"} /> : (isEditTemplate ? 'Update' : isCloneTemplate ? "Clone" : 'Submit')}
            </Button>
          </div>
        </FooterComponent>
      </Wrapper>
    </>
  );
};

export default withFormHoc(CreateTemplate);
