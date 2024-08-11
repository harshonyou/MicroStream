/**
 */
package Y3886186;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see Y3886186.Y3886186Factory
 * @model kind="package"
 * @generated
 */
public interface Y3886186Package extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "Y3886186";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "Y3886186";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "smp";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	Y3886186Package eINSTANCE = Y3886186.impl.Y3886186PackageImpl.init();

	/**
	 * The meta object id for the '{@link Y3886186.impl.EventImpl <em>Event</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Y3886186.impl.EventImpl
	 * @see Y3886186.impl.Y3886186PackageImpl#getEvent()
	 * @generated
	 */
	int EVENT = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT__NAME = 0;

	/**
	 * The feature id for the '<em><b>Fields</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT__FIELDS = 1;

	/**
	 * The number of structural features of the '<em>Event</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Event</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link Y3886186.impl.EventStreamImpl <em>Event Stream</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Y3886186.impl.EventStreamImpl
	 * @see Y3886186.impl.Y3886186PackageImpl#getEventStream()
	 * @generated
	 */
	int EVENT_STREAM = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_STREAM__NAME = 0;

	/**
	 * The feature id for the '<em><b>Event Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_STREAM__EVENT_TYPE = 1;

	/**
	 * The number of structural features of the '<em>Event Stream</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_STREAM_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Event Stream</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_STREAM_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link Y3886186.impl.ApiErrorImpl <em>Api Error</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Y3886186.impl.ApiErrorImpl
	 * @see Y3886186.impl.Y3886186PackageImpl#getApiError()
	 * @generated
	 */
	int API_ERROR = 2;

	/**
	 * The feature id for the '<em><b>Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int API_ERROR__CODE = 0;

	/**
	 * The feature id for the '<em><b>Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int API_ERROR__MESSAGE = 1;

	/**
	 * The number of structural features of the '<em>Api Error</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int API_ERROR_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Api Error</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int API_ERROR_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link Y3886186.impl.ApiResourceImpl <em>Api Resource</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Y3886186.impl.ApiResourceImpl
	 * @see Y3886186.impl.Y3886186PackageImpl#getApiResource()
	 * @generated
	 */
	int API_RESOURCE = 3;

	/**
	 * The feature id for the '<em><b>Method</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int API_RESOURCE__METHOD = 0;

	/**
	 * The feature id for the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int API_RESOURCE__PATH = 1;

	/**
	 * The feature id for the '<em><b>Request Format</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int API_RESOURCE__REQUEST_FORMAT = 2;

	/**
	 * The feature id for the '<em><b>Response Format</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int API_RESOURCE__RESPONSE_FORMAT = 3;

	/**
	 * The feature id for the '<em><b>Request Params</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int API_RESOURCE__REQUEST_PARAMS = 4;

	/**
	 * The feature id for the '<em><b>Response Params</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int API_RESOURCE__RESPONSE_PARAMS = 5;

	/**
	 * The feature id for the '<em><b>Error Responses</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int API_RESOURCE__ERROR_RESPONSES = 6;

	/**
	 * The number of structural features of the '<em>Api Resource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int API_RESOURCE_FEATURE_COUNT = 7;

	/**
	 * The number of operations of the '<em>Api Resource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int API_RESOURCE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link Y3886186.impl.ContainerizationImpl <em>Containerization</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Y3886186.impl.ContainerizationImpl
	 * @see Y3886186.impl.Y3886186PackageImpl#getContainerization()
	 * @generated
	 */
	int CONTAINERIZATION = 4;

	/**
	 * The feature id for the '<em><b>Technology</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINERIZATION__TECHNOLOGY = 0;

	/**
	 * The feature id for the '<em><b>Container Configs</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINERIZATION__CONTAINER_CONFIGS = 1;

	/**
	 * The feature id for the '<em><b>Environment Variables</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINERIZATION__ENVIRONMENT_VARIABLES = 2;

	/**
	 * The feature id for the '<em><b>Depends On</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINERIZATION__DEPENDS_ON = 3;

	/**
	 * The feature id for the '<em><b>Networks</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINERIZATION__NETWORKS = 4;

	/**
	 * The number of structural features of the '<em>Containerization</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINERIZATION_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Containerization</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINERIZATION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link Y3886186.impl.MicroserviceImpl <em>Microservice</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Y3886186.impl.MicroserviceImpl
	 * @see Y3886186.impl.Y3886186PackageImpl#getMicroservice()
	 * @generated
	 */
	int MICROSERVICE = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MICROSERVICE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MICROSERVICE__DESCRIPTION = 1;

	/**
	 * The feature id for the '<em><b>Communication Patterns</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MICROSERVICE__COMMUNICATION_PATTERNS = 2;

	/**
	 * The feature id for the '<em><b>Subscribes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MICROSERVICE__SUBSCRIBES = 3;

	/**
	 * The feature id for the '<em><b>Publishes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MICROSERVICE__PUBLISHES = 4;

	/**
	 * The feature id for the '<em><b>Api Resources</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MICROSERVICE__API_RESOURCES = 5;

	/**
	 * The feature id for the '<em><b>Containerizatin</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MICROSERVICE__CONTAINERIZATIN = 6;

	/**
	 * The number of structural features of the '<em>Microservice</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MICROSERVICE_FEATURE_COUNT = 7;

	/**
	 * The number of operations of the '<em>Microservice</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MICROSERVICE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link Y3886186.impl.ModelImpl <em>Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Y3886186.impl.ModelImpl
	 * @see Y3886186.impl.Y3886186PackageImpl#getModel()
	 * @generated
	 */
	int MODEL = 6;

	/**
	 * The feature id for the '<em><b>Microservices</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__MICROSERVICES = 0;

	/**
	 * The feature id for the '<em><b>Event Streams</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__EVENT_STREAMS = 1;

	/**
	 * The feature id for the '<em><b>Events</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__EVENTS = 2;

	/**
	 * The number of structural features of the '<em>Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link Y3886186.HttpMethod <em>Http Method</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Y3886186.HttpMethod
	 * @see Y3886186.impl.Y3886186PackageImpl#getHttpMethod()
	 * @generated
	 */
	int HTTP_METHOD = 7;

	/**
	 * The meta object id for the '{@link Y3886186.CommunicationPattern <em>Communication Pattern</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Y3886186.CommunicationPattern
	 * @see Y3886186.impl.Y3886186PackageImpl#getCommunicationPattern()
	 * @generated
	 */
	int COMMUNICATION_PATTERN = 8;


	/**
	 * Returns the meta object for class '{@link Y3886186.Event <em>Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Event</em>'.
	 * @see Y3886186.Event
	 * @generated
	 */
	EClass getEvent();

	/**
	 * Returns the meta object for the attribute '{@link Y3886186.Event#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see Y3886186.Event#getName()
	 * @see #getEvent()
	 * @generated
	 */
	EAttribute getEvent_Name();

	/**
	 * Returns the meta object for the map '{@link Y3886186.Event#getFields <em>Fields</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Fields</em>'.
	 * @see Y3886186.Event#getFields()
	 * @see #getEvent()
	 * @generated
	 */
	EReference getEvent_Fields();

	/**
	 * Returns the meta object for class '{@link Y3886186.EventStream <em>Event Stream</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Event Stream</em>'.
	 * @see Y3886186.EventStream
	 * @generated
	 */
	EClass getEventStream();

	/**
	 * Returns the meta object for the attribute '{@link Y3886186.EventStream#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see Y3886186.EventStream#getName()
	 * @see #getEventStream()
	 * @generated
	 */
	EAttribute getEventStream_Name();

	/**
	 * Returns the meta object for the reference '{@link Y3886186.EventStream#getEventType <em>Event Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Event Type</em>'.
	 * @see Y3886186.EventStream#getEventType()
	 * @see #getEventStream()
	 * @generated
	 */
	EReference getEventStream_EventType();

	/**
	 * Returns the meta object for class '{@link Y3886186.ApiError <em>Api Error</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Api Error</em>'.
	 * @see Y3886186.ApiError
	 * @generated
	 */
	EClass getApiError();

	/**
	 * Returns the meta object for the attribute '{@link Y3886186.ApiError#getCode <em>Code</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Code</em>'.
	 * @see Y3886186.ApiError#getCode()
	 * @see #getApiError()
	 * @generated
	 */
	EAttribute getApiError_Code();

	/**
	 * Returns the meta object for the attribute '{@link Y3886186.ApiError#getMessage <em>Message</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Message</em>'.
	 * @see Y3886186.ApiError#getMessage()
	 * @see #getApiError()
	 * @generated
	 */
	EAttribute getApiError_Message();

	/**
	 * Returns the meta object for class '{@link Y3886186.ApiResource <em>Api Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Api Resource</em>'.
	 * @see Y3886186.ApiResource
	 * @generated
	 */
	EClass getApiResource();

	/**
	 * Returns the meta object for the attribute '{@link Y3886186.ApiResource#getMethod <em>Method</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Method</em>'.
	 * @see Y3886186.ApiResource#getMethod()
	 * @see #getApiResource()
	 * @generated
	 */
	EAttribute getApiResource_Method();

	/**
	 * Returns the meta object for the attribute '{@link Y3886186.ApiResource#getPath <em>Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Path</em>'.
	 * @see Y3886186.ApiResource#getPath()
	 * @see #getApiResource()
	 * @generated
	 */
	EAttribute getApiResource_Path();

	/**
	 * Returns the meta object for the attribute '{@link Y3886186.ApiResource#getRequestFormat <em>Request Format</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Request Format</em>'.
	 * @see Y3886186.ApiResource#getRequestFormat()
	 * @see #getApiResource()
	 * @generated
	 */
	EAttribute getApiResource_RequestFormat();

	/**
	 * Returns the meta object for the attribute '{@link Y3886186.ApiResource#getResponseFormat <em>Response Format</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Response Format</em>'.
	 * @see Y3886186.ApiResource#getResponseFormat()
	 * @see #getApiResource()
	 * @generated
	 */
	EAttribute getApiResource_ResponseFormat();

	/**
	 * Returns the meta object for the map '{@link Y3886186.ApiResource#getRequestParams <em>Request Params</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Request Params</em>'.
	 * @see Y3886186.ApiResource#getRequestParams()
	 * @see #getApiResource()
	 * @generated
	 */
	EReference getApiResource_RequestParams();

	/**
	 * Returns the meta object for the map '{@link Y3886186.ApiResource#getResponseParams <em>Response Params</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Response Params</em>'.
	 * @see Y3886186.ApiResource#getResponseParams()
	 * @see #getApiResource()
	 * @generated
	 */
	EReference getApiResource_ResponseParams();

	/**
	 * Returns the meta object for the containment reference list '{@link Y3886186.ApiResource#getErrorResponses <em>Error Responses</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Error Responses</em>'.
	 * @see Y3886186.ApiResource#getErrorResponses()
	 * @see #getApiResource()
	 * @generated
	 */
	EReference getApiResource_ErrorResponses();

	/**
	 * Returns the meta object for class '{@link Y3886186.Containerization <em>Containerization</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Containerization</em>'.
	 * @see Y3886186.Containerization
	 * @generated
	 */
	EClass getContainerization();

	/**
	 * Returns the meta object for the attribute '{@link Y3886186.Containerization#getTechnology <em>Technology</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Technology</em>'.
	 * @see Y3886186.Containerization#getTechnology()
	 * @see #getContainerization()
	 * @generated
	 */
	EAttribute getContainerization_Technology();

	/**
	 * Returns the meta object for the map '{@link Y3886186.Containerization#getContainerConfigs <em>Container Configs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Container Configs</em>'.
	 * @see Y3886186.Containerization#getContainerConfigs()
	 * @see #getContainerization()
	 * @generated
	 */
	EReference getContainerization_ContainerConfigs();

	/**
	 * Returns the meta object for the map '{@link Y3886186.Containerization#getEnvironmentVariables <em>Environment Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Environment Variables</em>'.
	 * @see Y3886186.Containerization#getEnvironmentVariables()
	 * @see #getContainerization()
	 * @generated
	 */
	EReference getContainerization_EnvironmentVariables();

	/**
	 * Returns the meta object for the attribute list '{@link Y3886186.Containerization#getDependsOn <em>Depends On</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Depends On</em>'.
	 * @see Y3886186.Containerization#getDependsOn()
	 * @see #getContainerization()
	 * @generated
	 */
	EAttribute getContainerization_DependsOn();

	/**
	 * Returns the meta object for the attribute list '{@link Y3886186.Containerization#getNetworks <em>Networks</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Networks</em>'.
	 * @see Y3886186.Containerization#getNetworks()
	 * @see #getContainerization()
	 * @generated
	 */
	EAttribute getContainerization_Networks();

	/**
	 * Returns the meta object for class '{@link Y3886186.Microservice <em>Microservice</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Microservice</em>'.
	 * @see Y3886186.Microservice
	 * @generated
	 */
	EClass getMicroservice();

	/**
	 * Returns the meta object for the attribute '{@link Y3886186.Microservice#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see Y3886186.Microservice#getName()
	 * @see #getMicroservice()
	 * @generated
	 */
	EAttribute getMicroservice_Name();

	/**
	 * Returns the meta object for the attribute '{@link Y3886186.Microservice#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see Y3886186.Microservice#getDescription()
	 * @see #getMicroservice()
	 * @generated
	 */
	EAttribute getMicroservice_Description();

	/**
	 * Returns the meta object for the attribute list '{@link Y3886186.Microservice#getCommunicationPatterns <em>Communication Patterns</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Communication Patterns</em>'.
	 * @see Y3886186.Microservice#getCommunicationPatterns()
	 * @see #getMicroservice()
	 * @generated
	 */
	EAttribute getMicroservice_CommunicationPatterns();

	/**
	 * Returns the meta object for the reference list '{@link Y3886186.Microservice#getSubscribes <em>Subscribes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Subscribes</em>'.
	 * @see Y3886186.Microservice#getSubscribes()
	 * @see #getMicroservice()
	 * @generated
	 */
	EReference getMicroservice_Subscribes();

	/**
	 * Returns the meta object for the reference list '{@link Y3886186.Microservice#getPublishes <em>Publishes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Publishes</em>'.
	 * @see Y3886186.Microservice#getPublishes()
	 * @see #getMicroservice()
	 * @generated
	 */
	EReference getMicroservice_Publishes();

	/**
	 * Returns the meta object for the containment reference list '{@link Y3886186.Microservice#getApiResources <em>Api Resources</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Api Resources</em>'.
	 * @see Y3886186.Microservice#getApiResources()
	 * @see #getMicroservice()
	 * @generated
	 */
	EReference getMicroservice_ApiResources();

	/**
	 * Returns the meta object for the containment reference '{@link Y3886186.Microservice#getContainerizatin <em>Containerizatin</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Containerizatin</em>'.
	 * @see Y3886186.Microservice#getContainerizatin()
	 * @see #getMicroservice()
	 * @generated
	 */
	EReference getMicroservice_Containerizatin();

	/**
	 * Returns the meta object for class '{@link Y3886186.Model <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model</em>'.
	 * @see Y3886186.Model
	 * @generated
	 */
	EClass getModel();

	/**
	 * Returns the meta object for the containment reference list '{@link Y3886186.Model#getMicroservices <em>Microservices</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Microservices</em>'.
	 * @see Y3886186.Model#getMicroservices()
	 * @see #getModel()
	 * @generated
	 */
	EReference getModel_Microservices();

	/**
	 * Returns the meta object for the containment reference list '{@link Y3886186.Model#getEventStreams <em>Event Streams</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Event Streams</em>'.
	 * @see Y3886186.Model#getEventStreams()
	 * @see #getModel()
	 * @generated
	 */
	EReference getModel_EventStreams();

	/**
	 * Returns the meta object for the containment reference list '{@link Y3886186.Model#getEvents <em>Events</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Events</em>'.
	 * @see Y3886186.Model#getEvents()
	 * @see #getModel()
	 * @generated
	 */
	EReference getModel_Events();

	/**
	 * Returns the meta object for enum '{@link Y3886186.HttpMethod <em>Http Method</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Http Method</em>'.
	 * @see Y3886186.HttpMethod
	 * @generated
	 */
	EEnum getHttpMethod();

	/**
	 * Returns the meta object for enum '{@link Y3886186.CommunicationPattern <em>Communication Pattern</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Communication Pattern</em>'.
	 * @see Y3886186.CommunicationPattern
	 * @generated
	 */
	EEnum getCommunicationPattern();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	Y3886186Factory getY3886186Factory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link Y3886186.impl.EventImpl <em>Event</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Y3886186.impl.EventImpl
		 * @see Y3886186.impl.Y3886186PackageImpl#getEvent()
		 * @generated
		 */
		EClass EVENT = eINSTANCE.getEvent();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVENT__NAME = eINSTANCE.getEvent_Name();

		/**
		 * The meta object literal for the '<em><b>Fields</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EVENT__FIELDS = eINSTANCE.getEvent_Fields();

		/**
		 * The meta object literal for the '{@link Y3886186.impl.EventStreamImpl <em>Event Stream</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Y3886186.impl.EventStreamImpl
		 * @see Y3886186.impl.Y3886186PackageImpl#getEventStream()
		 * @generated
		 */
		EClass EVENT_STREAM = eINSTANCE.getEventStream();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVENT_STREAM__NAME = eINSTANCE.getEventStream_Name();

		/**
		 * The meta object literal for the '<em><b>Event Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EVENT_STREAM__EVENT_TYPE = eINSTANCE.getEventStream_EventType();

		/**
		 * The meta object literal for the '{@link Y3886186.impl.ApiErrorImpl <em>Api Error</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Y3886186.impl.ApiErrorImpl
		 * @see Y3886186.impl.Y3886186PackageImpl#getApiError()
		 * @generated
		 */
		EClass API_ERROR = eINSTANCE.getApiError();

		/**
		 * The meta object literal for the '<em><b>Code</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute API_ERROR__CODE = eINSTANCE.getApiError_Code();

		/**
		 * The meta object literal for the '<em><b>Message</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute API_ERROR__MESSAGE = eINSTANCE.getApiError_Message();

		/**
		 * The meta object literal for the '{@link Y3886186.impl.ApiResourceImpl <em>Api Resource</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Y3886186.impl.ApiResourceImpl
		 * @see Y3886186.impl.Y3886186PackageImpl#getApiResource()
		 * @generated
		 */
		EClass API_RESOURCE = eINSTANCE.getApiResource();

		/**
		 * The meta object literal for the '<em><b>Method</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute API_RESOURCE__METHOD = eINSTANCE.getApiResource_Method();

		/**
		 * The meta object literal for the '<em><b>Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute API_RESOURCE__PATH = eINSTANCE.getApiResource_Path();

		/**
		 * The meta object literal for the '<em><b>Request Format</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute API_RESOURCE__REQUEST_FORMAT = eINSTANCE.getApiResource_RequestFormat();

		/**
		 * The meta object literal for the '<em><b>Response Format</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute API_RESOURCE__RESPONSE_FORMAT = eINSTANCE.getApiResource_ResponseFormat();

		/**
		 * The meta object literal for the '<em><b>Request Params</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference API_RESOURCE__REQUEST_PARAMS = eINSTANCE.getApiResource_RequestParams();

		/**
		 * The meta object literal for the '<em><b>Response Params</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference API_RESOURCE__RESPONSE_PARAMS = eINSTANCE.getApiResource_ResponseParams();

		/**
		 * The meta object literal for the '<em><b>Error Responses</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference API_RESOURCE__ERROR_RESPONSES = eINSTANCE.getApiResource_ErrorResponses();

		/**
		 * The meta object literal for the '{@link Y3886186.impl.ContainerizationImpl <em>Containerization</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Y3886186.impl.ContainerizationImpl
		 * @see Y3886186.impl.Y3886186PackageImpl#getContainerization()
		 * @generated
		 */
		EClass CONTAINERIZATION = eINSTANCE.getContainerization();

		/**
		 * The meta object literal for the '<em><b>Technology</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTAINERIZATION__TECHNOLOGY = eINSTANCE.getContainerization_Technology();

		/**
		 * The meta object literal for the '<em><b>Container Configs</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINERIZATION__CONTAINER_CONFIGS = eINSTANCE.getContainerization_ContainerConfigs();

		/**
		 * The meta object literal for the '<em><b>Environment Variables</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINERIZATION__ENVIRONMENT_VARIABLES = eINSTANCE.getContainerization_EnvironmentVariables();

		/**
		 * The meta object literal for the '<em><b>Depends On</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTAINERIZATION__DEPENDS_ON = eINSTANCE.getContainerization_DependsOn();

		/**
		 * The meta object literal for the '<em><b>Networks</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTAINERIZATION__NETWORKS = eINSTANCE.getContainerization_Networks();

		/**
		 * The meta object literal for the '{@link Y3886186.impl.MicroserviceImpl <em>Microservice</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Y3886186.impl.MicroserviceImpl
		 * @see Y3886186.impl.Y3886186PackageImpl#getMicroservice()
		 * @generated
		 */
		EClass MICROSERVICE = eINSTANCE.getMicroservice();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MICROSERVICE__NAME = eINSTANCE.getMicroservice_Name();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MICROSERVICE__DESCRIPTION = eINSTANCE.getMicroservice_Description();

		/**
		 * The meta object literal for the '<em><b>Communication Patterns</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MICROSERVICE__COMMUNICATION_PATTERNS = eINSTANCE.getMicroservice_CommunicationPatterns();

		/**
		 * The meta object literal for the '<em><b>Subscribes</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MICROSERVICE__SUBSCRIBES = eINSTANCE.getMicroservice_Subscribes();

		/**
		 * The meta object literal for the '<em><b>Publishes</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MICROSERVICE__PUBLISHES = eINSTANCE.getMicroservice_Publishes();

		/**
		 * The meta object literal for the '<em><b>Api Resources</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MICROSERVICE__API_RESOURCES = eINSTANCE.getMicroservice_ApiResources();

		/**
		 * The meta object literal for the '<em><b>Containerizatin</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MICROSERVICE__CONTAINERIZATIN = eINSTANCE.getMicroservice_Containerizatin();

		/**
		 * The meta object literal for the '{@link Y3886186.impl.ModelImpl <em>Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Y3886186.impl.ModelImpl
		 * @see Y3886186.impl.Y3886186PackageImpl#getModel()
		 * @generated
		 */
		EClass MODEL = eINSTANCE.getModel();

		/**
		 * The meta object literal for the '<em><b>Microservices</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL__MICROSERVICES = eINSTANCE.getModel_Microservices();

		/**
		 * The meta object literal for the '<em><b>Event Streams</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL__EVENT_STREAMS = eINSTANCE.getModel_EventStreams();

		/**
		 * The meta object literal for the '<em><b>Events</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL__EVENTS = eINSTANCE.getModel_Events();

		/**
		 * The meta object literal for the '{@link Y3886186.HttpMethod <em>Http Method</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Y3886186.HttpMethod
		 * @see Y3886186.impl.Y3886186PackageImpl#getHttpMethod()
		 * @generated
		 */
		EEnum HTTP_METHOD = eINSTANCE.getHttpMethod();

		/**
		 * The meta object literal for the '{@link Y3886186.CommunicationPattern <em>Communication Pattern</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Y3886186.CommunicationPattern
		 * @see Y3886186.impl.Y3886186PackageImpl#getCommunicationPattern()
		 * @generated
		 */
		EEnum COMMUNICATION_PATTERN = eINSTANCE.getCommunicationPattern();

	}

} //Y3886186Package
