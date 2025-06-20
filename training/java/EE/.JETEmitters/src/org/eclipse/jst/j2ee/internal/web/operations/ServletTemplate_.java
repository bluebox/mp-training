package org.eclipse.jst.j2ee.internal.web.operations;

import java.util.*;
import org.eclipse.jst.j2ee.internal.common.operations.*;

public class ServletTemplate_
{
  protected static String nl;
  public static synchronized ServletTemplate_ create(String lineSeparator)
  {
    nl = lineSeparator;
    ServletTemplate_ result = new ServletTemplate_();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "package ";
  protected final String TEXT_2 = ";";
  protected final String TEXT_3 = NL;
  protected final String TEXT_4 = NL + "import ";
  protected final String TEXT_5 = "/**" + NL + " * Servlet implementation class ";
  protected final String TEXT_6 = NL + " *" + NL + " * @web.servlet" + NL + " *   name=\"";
  protected final String TEXT_7 = "\"" + NL + " *   display-name=\"";
  protected final String TEXT_8 = "\"";
  protected final String TEXT_9 = NL + " *   description=\"";
  protected final String TEXT_10 = NL + " *" + NL + " * @web.servlet-mapping" + NL + " *   url-pattern=\"";
  protected final String TEXT_11 = NL + " *" + NL + " * @web.servlet-init-param" + NL + " *    name=\"";
  protected final String TEXT_12 = "\"" + NL + " *    value=\"";
  protected final String TEXT_13 = NL + " *    description=\"";
  protected final String TEXT_14 = NL + " */";
  protected final String TEXT_15 = NL + "@WebServlet";
  protected final String TEXT_16 = "(\"";
  protected final String TEXT_17 = "\")";
  protected final String TEXT_18 = "({ ";
  protected final String TEXT_19 = ", ";
  protected final String TEXT_20 = " })";
  protected final String TEXT_21 = "(";
  protected final String TEXT_22 = NL + "\t\t";
  protected final String TEXT_23 = " = \"";
  protected final String TEXT_24 = " = { ";
  protected final String TEXT_25 = NL + "\t\t\t\t";
  protected final String TEXT_26 = " ";
  protected final String TEXT_27 = "}";
  protected final String TEXT_28 = NL + "\t\t\t\t@WebInitParam(name = \"";
  protected final String TEXT_29 = "\", value = \"";
  protected final String TEXT_30 = ", description = \"";
  protected final String TEXT_31 = ")";
  protected final String TEXT_32 = NL + "\t\t}";
  protected final String TEXT_33 = " = ";
  protected final String TEXT_34 = NL + "public ";
  protected final String TEXT_35 = "abstract ";
  protected final String TEXT_36 = "final ";
  protected final String TEXT_37 = "class ";
  protected final String TEXT_38 = " extends ";
  protected final String TEXT_39 = " implements ";
  protected final String TEXT_40 = " {";
  protected final String TEXT_41 = NL + "\tprivate static final long serialVersionUID = 1L;";
  protected final String TEXT_42 = NL + NL + "    /**" + NL + "     * Default constructor. " + NL + "     */" + NL + "    public ";
  protected final String TEXT_43 = "() {" + NL + "        // TODO Auto-generated constructor stub" + NL + "    }";
  protected final String TEXT_44 = NL + "       " + NL + "    /**" + NL + "     * @see ";
  protected final String TEXT_45 = "#";
  protected final String TEXT_46 = ")" + NL + "     */" + NL + "    public ";
  protected final String TEXT_47 = ") {" + NL + "        super(";
  protected final String TEXT_48 = ");" + NL + "        // TODO Auto-generated constructor stub" + NL + "    }";
  protected final String TEXT_49 = NL + NL + "\t/**" + NL + "     * @see ";
  protected final String TEXT_50 = ") ";
  protected final String TEXT_51 = "throws ";
  protected final String TEXT_52 = " { ";
  protected final String TEXT_53 = NL + "         // TODO Auto-generated method stub";
  protected final String TEXT_54 = NL + "\t\t\treturn ";
  protected final String TEXT_55 = NL + "    }";
  protected final String TEXT_56 = NL + NL + "\t/**" + NL + "\t * @see Servlet#init(ServletConfig)" + NL + "\t */" + NL + "\tpublic void init(ServletConfig config) throws ServletException {" + NL + "\t\t// TODO Auto-generated method stub" + NL + "\t}";
  protected final String TEXT_57 = NL + NL + "\t/**" + NL + "\t * @see Servlet#destroy()" + NL + "\t */" + NL + "\tpublic void destroy() {" + NL + "\t\t// TODO Auto-generated method stub" + NL + "\t}";
  protected final String TEXT_58 = NL + NL + "\t/**" + NL + "\t * @see Servlet#getServletConfig()" + NL + "\t */" + NL + "\tpublic ServletConfig getServletConfig() {" + NL + "\t\t// TODO Auto-generated method stub" + NL + "\t\treturn null;" + NL + "\t}";
  protected final String TEXT_59 = NL + NL + "\t/**" + NL + "\t * @see Servlet#getServletInfo()" + NL + "\t */" + NL + "\tpublic String getServletInfo() {" + NL + "\t\t// TODO Auto-generated method stub" + NL + "\t\treturn null; " + NL + "\t}";
  protected final String TEXT_60 = NL + NL + "\t/**" + NL + "\t * @see Servlet#service(ServletRequest request, ServletResponse response)" + NL + "\t */" + NL + "\tpublic void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {" + NL + "\t\t// TODO Auto-generated method stub" + NL + "\t}";
  protected final String TEXT_61 = NL + NL + "\t/**" + NL + "\t * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)" + NL + "\t */" + NL + "\tprotected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {" + NL + "\t\t// TODO Auto-generated method stub" + NL + "\t}";
  protected final String TEXT_62 = NL + NL + "\t/**" + NL + "\t * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)" + NL + "\t */" + NL + "\tprotected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {" + NL + "\t\t// TODO Auto-generated method stub" + NL + "\t\tresponse.getWriter().append(\"Served at: \").append(request.getContextPath());" + NL + "\t}";
  protected final String TEXT_63 = NL + NL + "\t/**" + NL + "\t * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)" + NL + "\t */" + NL + "\tprotected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {" + NL + "\t\t// TODO Auto-generated method stub";
  protected final String TEXT_64 = NL + "\t\tdoGet(request, response);";
  protected final String TEXT_65 = NL + "\t}";
  protected final String TEXT_66 = NL + NL + "\t/**" + NL + "\t * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)" + NL + "\t */" + NL + "\tprotected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {" + NL + "\t\t// TODO Auto-generated method stub" + NL + "\t}";
  protected final String TEXT_67 = NL + NL + "\t/**" + NL + "\t * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)" + NL + "\t */" + NL + "\tprotected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {" + NL + "\t\t// TODO Auto-generated method stub" + NL + "\t}";
  protected final String TEXT_68 = NL + NL + "\t/**" + NL + "\t * @see HttpServlet#doHead(HttpServletRequest, HttpServletResponse)" + NL + "\t */" + NL + "\tprotected void doHead(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {" + NL + "\t\t// TODO Auto-generated method stub" + NL + "\t}";
  protected final String TEXT_69 = NL + NL + "\t/**" + NL + "\t * @see HttpServlet#doOptions(HttpServletRequest, HttpServletResponse)" + NL + "\t */" + NL + "\tprotected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {" + NL + "\t\t// TODO Auto-generated method stub" + NL + "\t}";
  protected final String TEXT_70 = NL + NL + "\t/**" + NL + "\t * @see HttpServlet#doTrace(HttpServletRequest, HttpServletResponse)" + NL + "\t */" + NL + "\tprotected void doTrace(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {" + NL + "\t\t// TODO Auto-generated method stub" + NL + "\t}";
  protected final String TEXT_71 = NL + NL + "}";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
     CreateServletTemplateModel model = (CreateServletTemplateModel) argument; 
    
	model.removeFlags(CreateJavaEEArtifactTemplateModel.FLAG_QUALIFIED_SUPERCLASS_NAME); 

    
	if (model.getJavaPackageName() != null && model.getJavaPackageName().length() > 0) {

    stringBuffer.append(TEXT_1);
    stringBuffer.append( model.getJavaPackageName() );
    stringBuffer.append(TEXT_2);
    
	}

    stringBuffer.append(TEXT_3);
     
	Collection<String> imports = model.getImports();
	for (String anImport : imports) { 

    stringBuffer.append(TEXT_4);
    stringBuffer.append( anImport );
    stringBuffer.append(TEXT_2);
     
	}

    stringBuffer.append(TEXT_3);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(TEXT_5);
    stringBuffer.append( model.getClassName() );
     
	if (model.isAnnotated()) { 

    stringBuffer.append(TEXT_6);
    stringBuffer.append( model.getServletName() );
    stringBuffer.append(TEXT_7);
    stringBuffer.append( model.getServletName() );
    stringBuffer.append(TEXT_8);
     
		if (model.getDescription() != null && model.getDescription().length() > 0) { 

    stringBuffer.append(TEXT_9);
    stringBuffer.append( model.getDescription() );
    stringBuffer.append(TEXT_8);
     
		} 
		
		List<String[]> mappings = model.getServletMappings();
 		if (mappings != null && mappings.size() > 0) {
			for (int i = 0; i < mappings.size(); i++) {
				String map = model.getServletMapping(i); 
    stringBuffer.append(TEXT_10);
    stringBuffer.append( map );
    stringBuffer.append(TEXT_8);
     
			} 
		}
 		List<String[]> initParams = model.getInitParams();
 		if (initParams != null && initParams.size() > 0) {
    		for (int i = 0; i < initParams.size(); i++) {
				String name = model.getInitParam(i, CreateServletTemplateModel.NAME);
				String value = model.getInitParam(i, CreateServletTemplateModel.VALUE);
 				String description = model.getInitParam(i, CreateServletTemplateModel.DESCRIPTION); 

    stringBuffer.append(TEXT_11);
    stringBuffer.append( name );
    stringBuffer.append(TEXT_12);
    stringBuffer.append( value );
    stringBuffer.append(TEXT_8);
     
				if (description != null && description.length() > 0) { 

    stringBuffer.append(TEXT_13);
    stringBuffer.append( description );
    stringBuffer.append(TEXT_8);
    
				}
			} 
		} 
	} 

    stringBuffer.append(TEXT_14);
     
	if ("3.0".equals(model.getJavaEEVersion()) || "3.1".equals(model.getJavaEEVersion()) || "4.0".equals(model.getJavaEEVersion())) { 

    stringBuffer.append(TEXT_15);
    
		Map<String, Object> params = model.getClassAnnotationParams();
		if (params.size() == 1 && params.containsKey(CreateServletTemplateModel.ATT_URL_PATTERNS)) {
			List<String[]> mappings = (List<String[]>) params.get(CreateServletTemplateModel.ATT_URL_PATTERNS);
			if (mappings.size() == 1) {
				String value = mappings.get(0)[0];

    stringBuffer.append(TEXT_16);
    stringBuffer.append( value );
    stringBuffer.append(TEXT_17);
    
			} else {

    stringBuffer.append(TEXT_18);
    
				boolean needComma = false;
				for (String[] mapping : mappings) {
					if (needComma) {

    stringBuffer.append(TEXT_19);
    
					}

    stringBuffer.append(TEXT_8);
    stringBuffer.append( mapping[0] );
    stringBuffer.append(TEXT_8);
    
					needComma = true;
				}

    stringBuffer.append(TEXT_20);
    
			}
		} else if (!params.isEmpty()) { 

    stringBuffer.append(TEXT_21);
    
			Set<String> keys = params.keySet();
			boolean needNewLine = keys.contains(CreateServletTemplateModel.ATT_INIT_PARAMS) || 
					(keys.contains(CreateServletTemplateModel.ATT_URL_PATTERNS) && 
							((List<String[]>) params.get(CreateServletTemplateModel.ATT_URL_PATTERNS)).size() > 1);
			boolean needComma = false;
			for (String key : keys) {
				if (needComma) {

    stringBuffer.append(TEXT_19);
    
				}
				
				if (needNewLine) {

    stringBuffer.append(TEXT_22);
    
				} 
			
				if (key.equals(CreateServletTemplateModel.ATT_NAME) || key.equals(CreateServletTemplateModel.ATT_DESCRIPTION)) { 
					String value = (String) params.get(key);

    stringBuffer.append( key );
    stringBuffer.append(TEXT_23);
    stringBuffer.append( value );
    stringBuffer.append(TEXT_8);
    
				} else if (key.equals(CreateServletTemplateModel.ATT_URL_PATTERNS)) {

    stringBuffer.append( key );
    stringBuffer.append(TEXT_24);
    
					List<String[]> mappings = (List<String[]>) params.get(key);
					boolean needComma2 = false;
					boolean needNewLine2 = mappings.size() > 1;
					for (String[] mapping : mappings) {
						if (needComma2) {

    stringBuffer.append(TEXT_19);
    
						}
				
						if (needNewLine2) {

    stringBuffer.append(TEXT_25);
    
						} 

    stringBuffer.append(TEXT_8);
    stringBuffer.append( mapping[0] );
    stringBuffer.append(TEXT_8);
    				
						needComma2 = true;
					}
				
					if (needNewLine2) {

    stringBuffer.append(TEXT_22);
    
					} else {

    stringBuffer.append(TEXT_26);
    
					}

    stringBuffer.append(TEXT_27);
    
				} else if (key.equals(CreateServletTemplateModel.ATT_INIT_PARAMS)) {

    stringBuffer.append( key );
    stringBuffer.append(TEXT_24);
    
					List<String[]> initParams = (List<String[]>) params.get(key);
					boolean needComma2 = false;
					for (String[] initParam : initParams) {
						if (needComma2) {

    stringBuffer.append(TEXT_19);
    
						}
						
						String name = initParam[CreateServletTemplateModel.NAME];
						String value = initParam[CreateServletTemplateModel.VALUE];
						String description = initParam[CreateServletTemplateModel.DESCRIPTION];

    stringBuffer.append(TEXT_28);
    stringBuffer.append( name );
    stringBuffer.append(TEXT_29);
    stringBuffer.append( value );
    stringBuffer.append(TEXT_8);
    				
						if (description != null && description.length() > 0) {

    stringBuffer.append(TEXT_30);
    stringBuffer.append( description );
    stringBuffer.append(TEXT_8);
    
						}

    stringBuffer.append(TEXT_31);
    
						needComma2 = true;
					}

    stringBuffer.append(TEXT_32);
    
				}
				
				else if (key.equals(CreateServletTemplateModel.ATT_ASYNC_SUPPORT)) {
					Boolean value =(Boolean) params.get(key);
				    if (value){

    stringBuffer.append( key );
    stringBuffer.append(TEXT_33);
    stringBuffer.append( value );
    				    }				
				}
				needComma = true;
  			}

    stringBuffer.append(TEXT_31);
    
		}
	}

    
	if (model.isPublic()) { 

    stringBuffer.append(TEXT_34);
     
	} 

	if (model.isAbstract()) { 

    stringBuffer.append(TEXT_35);
    
	}

	if (model.isFinal()) {

    stringBuffer.append(TEXT_36);
    
	}

    stringBuffer.append(TEXT_37);
    stringBuffer.append( model.getClassName() );
    
	String superClass = model.getSuperclassName();
 	if (superClass != null && superClass.length() > 0) {

    stringBuffer.append(TEXT_38);
    stringBuffer.append( superClass );
    
	}

	List<String> interfaces = model.getInterfaces(); 
 	if ( interfaces.size() > 0) { 

    stringBuffer.append(TEXT_39);
    
	}
	
 	for (int i = 0; i < interfaces.size(); i++) {
   		String INTERFACE = interfaces.get(i);
   		if (i > 0) {

    stringBuffer.append(TEXT_19);
    
		}

    stringBuffer.append( INTERFACE );
    
	}

    stringBuffer.append(TEXT_40);
     
	if (model.isGenericServletSuperclass()) { 

    stringBuffer.append(TEXT_41);
     
	} 

     
	if (!model.hasEmptySuperclassConstructor()) { 

    stringBuffer.append(TEXT_42);
    stringBuffer.append( model.getClassName() );
    stringBuffer.append(TEXT_43);
     
	} 

	if (model.shouldGenSuperclassConstructors()) {
		List<Constructor> constructors = model.getConstructors();
		for (Constructor constructor : constructors) {
			if (constructor.isPublic() || constructor.isProtected()) { 

    stringBuffer.append(TEXT_44);
    stringBuffer.append( model.getSuperclassName() );
    stringBuffer.append(TEXT_45);
    stringBuffer.append( model.getSuperclassName() );
    stringBuffer.append(TEXT_21);
    stringBuffer.append( constructor.getParamsForJavadoc() );
    stringBuffer.append(TEXT_46);
    stringBuffer.append( model.getClassName() );
    stringBuffer.append(TEXT_21);
    stringBuffer.append( constructor.getParamsForDeclaration() );
    stringBuffer.append(TEXT_47);
    stringBuffer.append( constructor.getParamsForCall() );
    stringBuffer.append(TEXT_48);
    
			} 
		} 
	} 

    
	if (model.shouldImplementAbstractMethods()) {
		for (Method method : model.getUnimplementedMethods()) { 

    stringBuffer.append(TEXT_49);
    stringBuffer.append( method.getContainingJavaClass() );
    stringBuffer.append(TEXT_45);
    stringBuffer.append( method.getName() );
    stringBuffer.append(TEXT_21);
    stringBuffer.append( method.getParamsForJavadoc() );
    stringBuffer.append(TEXT_46);
    stringBuffer.append( method.getReturnType() );
    stringBuffer.append(TEXT_26);
    stringBuffer.append( method.getName() );
    stringBuffer.append(TEXT_21);
    stringBuffer.append( method.getParamsForDeclaration() );
    stringBuffer.append(TEXT_50);
      if (method.getExceptions().length() > 0){ 
    stringBuffer.append(TEXT_51);
    stringBuffer.append(method.getExceptions());
    stringBuffer.append(TEXT_52);
    }else {
    stringBuffer.append(TEXT_52);
     } 
    stringBuffer.append(TEXT_53);
     
			String defaultReturnValue = method.getDefaultReturnValue();
			if (defaultReturnValue != null) { 

    stringBuffer.append(TEXT_54);
    stringBuffer.append( defaultReturnValue );
    stringBuffer.append(TEXT_2);
    
			} 

    stringBuffer.append(TEXT_55);
     
		}
	} 

     if (model.shouldGenInit()) { 
    stringBuffer.append(TEXT_56);
     } 
     if (model.shouldGenDestroy()) { 
    stringBuffer.append(TEXT_57);
     } 
     if (model.shouldGenGetServletConfig()) { 
    stringBuffer.append(TEXT_58);
     } 
     if (model.shouldGenGetServletInfo()) { 
    stringBuffer.append(TEXT_59);
     } 
     if (model.shouldGenService() && !model.isHttpServletSuperclass()) { 
    stringBuffer.append(TEXT_60);
     } 
     if (model.shouldGenService() && model.isHttpServletSuperclass()) { 
    stringBuffer.append(TEXT_61);
     } 
     if (model.shouldGenDoGet()) { 
    stringBuffer.append(TEXT_62);
     } 
     if (model.shouldGenDoPost()) { 
    stringBuffer.append(TEXT_63);
     if (model.shouldGenDoGet()) { 
    stringBuffer.append(TEXT_64);
     } 
    stringBuffer.append(TEXT_65);
     } 
     if (model.shouldGenDoPut()) { 
    stringBuffer.append(TEXT_66);
     } 
     if (model.shouldGenDoDelete()) { 
    stringBuffer.append(TEXT_67);
     } 
     if (model.shouldGenDoHead()) { 
    stringBuffer.append(TEXT_68);
     } 
     if (model.shouldGenDoOptions()) { 
    stringBuffer.append(TEXT_69);
     } 
     if (model.shouldGenDoTrace()) { 
    stringBuffer.append(TEXT_70);
     } 
    stringBuffer.append(TEXT_71);
    stringBuffer.append(TEXT_3);
    return stringBuffer.toString();
  }
}
