/**
 */
package Y3886186.impl;

import Y3886186.ApiError;
import Y3886186.ApiResource;
import Y3886186.HttpMethod;
import Y3886186.Y3886186Package;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Api Resource</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link Y3886186.impl.ApiResourceImpl#getMethod <em>Method</em>}</li>
 *   <li>{@link Y3886186.impl.ApiResourceImpl#getPath <em>Path</em>}</li>
 *   <li>{@link Y3886186.impl.ApiResourceImpl#getRequestFormat <em>Request Format</em>}</li>
 *   <li>{@link Y3886186.impl.ApiResourceImpl#getResponseFormat <em>Response Format</em>}</li>
 *   <li>{@link Y3886186.impl.ApiResourceImpl#getRequestParams <em>Request Params</em>}</li>
 *   <li>{@link Y3886186.impl.ApiResourceImpl#getResponseParams <em>Response Params</em>}</li>
 *   <li>{@link Y3886186.impl.ApiResourceImpl#getErrorResponses <em>Error Responses</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ApiResourceImpl extends MinimalEObjectImpl.Container implements ApiResource {
	/**
	 * The default value of the '{@link #getMethod() <em>Method</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMethod()
	 * @generated
	 * @ordered
	 */
	protected static final HttpMethod METHOD_EDEFAULT = HttpMethod.GET;

	/**
	 * The cached value of the '{@link #getMethod() <em>Method</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMethod()
	 * @generated
	 * @ordered
	 */
	protected HttpMethod method = METHOD_EDEFAULT;

	/**
	 * The default value of the '{@link #getPath() <em>Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPath()
	 * @generated
	 * @ordered
	 */
	protected static final String PATH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPath() <em>Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPath()
	 * @generated
	 * @ordered
	 */
	protected String path = PATH_EDEFAULT;

	/**
	 * The default value of the '{@link #getRequestFormat() <em>Request Format</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequestFormat()
	 * @generated
	 * @ordered
	 */
	protected static final String REQUEST_FORMAT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRequestFormat() <em>Request Format</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequestFormat()
	 * @generated
	 * @ordered
	 */
	protected String requestFormat = REQUEST_FORMAT_EDEFAULT;

	/**
	 * The default value of the '{@link #getResponseFormat() <em>Response Format</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResponseFormat()
	 * @generated
	 * @ordered
	 */
	protected static final String RESPONSE_FORMAT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getResponseFormat() <em>Response Format</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResponseFormat()
	 * @generated
	 * @ordered
	 */
	protected String responseFormat = RESPONSE_FORMAT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getRequestParams() <em>Request Params</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequestParams()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> requestParams;

	/**
	 * The cached value of the '{@link #getResponseParams() <em>Response Params</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResponseParams()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> responseParams;

	/**
	 * The cached value of the '{@link #getErrorResponses() <em>Error Responses</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getErrorResponses()
	 * @generated
	 * @ordered
	 */
	protected EList<ApiError> errorResponses;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ApiResourceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Y3886186Package.Literals.API_RESOURCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HttpMethod getMethod() {
		return method;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMethod(HttpMethod newMethod) {
		HttpMethod oldMethod = method;
		method = newMethod == null ? METHOD_EDEFAULT : newMethod;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Y3886186Package.API_RESOURCE__METHOD, oldMethod, method));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPath() {
		return path;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPath(String newPath) {
		String oldPath = path;
		path = newPath;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Y3886186Package.API_RESOURCE__PATH, oldPath, path));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRequestFormat() {
		return requestFormat;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRequestFormat(String newRequestFormat) {
		String oldRequestFormat = requestFormat;
		requestFormat = newRequestFormat;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Y3886186Package.API_RESOURCE__REQUEST_FORMAT, oldRequestFormat, requestFormat));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getResponseFormat() {
		return responseFormat;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResponseFormat(String newResponseFormat) {
		String oldResponseFormat = responseFormat;
		responseFormat = newResponseFormat;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Y3886186Package.API_RESOURCE__RESPONSE_FORMAT, oldResponseFormat, responseFormat));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, String> getRequestParams() {
		if (requestParams == null) {
			requestParams = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, Y3886186Package.API_RESOURCE__REQUEST_PARAMS);
		}
		return requestParams;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, String> getResponseParams() {
		if (responseParams == null) {
			responseParams = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, Y3886186Package.API_RESOURCE__RESPONSE_PARAMS);
		}
		return responseParams;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ApiError> getErrorResponses() {
		if (errorResponses == null) {
			errorResponses = new EObjectContainmentEList<ApiError>(ApiError.class, this, Y3886186Package.API_RESOURCE__ERROR_RESPONSES);
		}
		return errorResponses;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Y3886186Package.API_RESOURCE__REQUEST_PARAMS:
				return ((InternalEList<?>)getRequestParams()).basicRemove(otherEnd, msgs);
			case Y3886186Package.API_RESOURCE__RESPONSE_PARAMS:
				return ((InternalEList<?>)getResponseParams()).basicRemove(otherEnd, msgs);
			case Y3886186Package.API_RESOURCE__ERROR_RESPONSES:
				return ((InternalEList<?>)getErrorResponses()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Y3886186Package.API_RESOURCE__METHOD:
				return getMethod();
			case Y3886186Package.API_RESOURCE__PATH:
				return getPath();
			case Y3886186Package.API_RESOURCE__REQUEST_FORMAT:
				return getRequestFormat();
			case Y3886186Package.API_RESOURCE__RESPONSE_FORMAT:
				return getResponseFormat();
			case Y3886186Package.API_RESOURCE__REQUEST_PARAMS:
				if (coreType) return getRequestParams();
				else return getRequestParams().map();
			case Y3886186Package.API_RESOURCE__RESPONSE_PARAMS:
				if (coreType) return getResponseParams();
				else return getResponseParams().map();
			case Y3886186Package.API_RESOURCE__ERROR_RESPONSES:
				return getErrorResponses();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case Y3886186Package.API_RESOURCE__METHOD:
				setMethod((HttpMethod)newValue);
				return;
			case Y3886186Package.API_RESOURCE__PATH:
				setPath((String)newValue);
				return;
			case Y3886186Package.API_RESOURCE__REQUEST_FORMAT:
				setRequestFormat((String)newValue);
				return;
			case Y3886186Package.API_RESOURCE__RESPONSE_FORMAT:
				setResponseFormat((String)newValue);
				return;
			case Y3886186Package.API_RESOURCE__REQUEST_PARAMS:
				((EStructuralFeature.Setting)getRequestParams()).set(newValue);
				return;
			case Y3886186Package.API_RESOURCE__RESPONSE_PARAMS:
				((EStructuralFeature.Setting)getResponseParams()).set(newValue);
				return;
			case Y3886186Package.API_RESOURCE__ERROR_RESPONSES:
				getErrorResponses().clear();
				getErrorResponses().addAll((Collection<? extends ApiError>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case Y3886186Package.API_RESOURCE__METHOD:
				setMethod(METHOD_EDEFAULT);
				return;
			case Y3886186Package.API_RESOURCE__PATH:
				setPath(PATH_EDEFAULT);
				return;
			case Y3886186Package.API_RESOURCE__REQUEST_FORMAT:
				setRequestFormat(REQUEST_FORMAT_EDEFAULT);
				return;
			case Y3886186Package.API_RESOURCE__RESPONSE_FORMAT:
				setResponseFormat(RESPONSE_FORMAT_EDEFAULT);
				return;
			case Y3886186Package.API_RESOURCE__REQUEST_PARAMS:
				getRequestParams().clear();
				return;
			case Y3886186Package.API_RESOURCE__RESPONSE_PARAMS:
				getResponseParams().clear();
				return;
			case Y3886186Package.API_RESOURCE__ERROR_RESPONSES:
				getErrorResponses().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case Y3886186Package.API_RESOURCE__METHOD:
				return method != METHOD_EDEFAULT;
			case Y3886186Package.API_RESOURCE__PATH:
				return PATH_EDEFAULT == null ? path != null : !PATH_EDEFAULT.equals(path);
			case Y3886186Package.API_RESOURCE__REQUEST_FORMAT:
				return REQUEST_FORMAT_EDEFAULT == null ? requestFormat != null : !REQUEST_FORMAT_EDEFAULT.equals(requestFormat);
			case Y3886186Package.API_RESOURCE__RESPONSE_FORMAT:
				return RESPONSE_FORMAT_EDEFAULT == null ? responseFormat != null : !RESPONSE_FORMAT_EDEFAULT.equals(responseFormat);
			case Y3886186Package.API_RESOURCE__REQUEST_PARAMS:
				return requestParams != null && !requestParams.isEmpty();
			case Y3886186Package.API_RESOURCE__RESPONSE_PARAMS:
				return responseParams != null && !responseParams.isEmpty();
			case Y3886186Package.API_RESOURCE__ERROR_RESPONSES:
				return errorResponses != null && !errorResponses.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (method: ");
		result.append(method);
		result.append(", path: ");
		result.append(path);
		result.append(", requestFormat: ");
		result.append(requestFormat);
		result.append(", responseFormat: ");
		result.append(responseFormat);
		result.append(')');
		return result.toString();
	}

} //ApiResourceImpl
