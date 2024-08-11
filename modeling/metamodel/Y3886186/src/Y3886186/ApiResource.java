/**
 */
package Y3886186;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Api Resource</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link Y3886186.ApiResource#getMethod <em>Method</em>}</li>
 *   <li>{@link Y3886186.ApiResource#getPath <em>Path</em>}</li>
 *   <li>{@link Y3886186.ApiResource#getRequestFormat <em>Request Format</em>}</li>
 *   <li>{@link Y3886186.ApiResource#getResponseFormat <em>Response Format</em>}</li>
 *   <li>{@link Y3886186.ApiResource#getRequestParams <em>Request Params</em>}</li>
 *   <li>{@link Y3886186.ApiResource#getResponseParams <em>Response Params</em>}</li>
 *   <li>{@link Y3886186.ApiResource#getErrorResponses <em>Error Responses</em>}</li>
 * </ul>
 *
 * @see Y3886186.Y3886186Package#getApiResource()
 * @model
 * @generated
 */
public interface ApiResource extends EObject {
	/**
	 * Returns the value of the '<em><b>Method</b></em>' attribute.
	 * The literals are from the enumeration {@link Y3886186.HttpMethod}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Method</em>' attribute.
	 * @see Y3886186.HttpMethod
	 * @see #setMethod(HttpMethod)
	 * @see Y3886186.Y3886186Package#getApiResource_Method()
	 * @model
	 * @generated
	 */
	HttpMethod getMethod();

	/**
	 * Sets the value of the '{@link Y3886186.ApiResource#getMethod <em>Method</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Method</em>' attribute.
	 * @see Y3886186.HttpMethod
	 * @see #getMethod()
	 * @generated
	 */
	void setMethod(HttpMethod value);

	/**
	 * Returns the value of the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Path</em>' attribute.
	 * @see #setPath(String)
	 * @see Y3886186.Y3886186Package#getApiResource_Path()
	 * @model
	 * @generated
	 */
	String getPath();

	/**
	 * Sets the value of the '{@link Y3886186.ApiResource#getPath <em>Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Path</em>' attribute.
	 * @see #getPath()
	 * @generated
	 */
	void setPath(String value);

	/**
	 * Returns the value of the '<em><b>Request Format</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Request Format</em>' attribute.
	 * @see #setRequestFormat(String)
	 * @see Y3886186.Y3886186Package#getApiResource_RequestFormat()
	 * @model
	 * @generated
	 */
	String getRequestFormat();

	/**
	 * Sets the value of the '{@link Y3886186.ApiResource#getRequestFormat <em>Request Format</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Request Format</em>' attribute.
	 * @see #getRequestFormat()
	 * @generated
	 */
	void setRequestFormat(String value);

	/**
	 * Returns the value of the '<em><b>Response Format</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Response Format</em>' attribute.
	 * @see #setResponseFormat(String)
	 * @see Y3886186.Y3886186Package#getApiResource_ResponseFormat()
	 * @model
	 * @generated
	 */
	String getResponseFormat();

	/**
	 * Sets the value of the '{@link Y3886186.ApiResource#getResponseFormat <em>Response Format</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Response Format</em>' attribute.
	 * @see #getResponseFormat()
	 * @generated
	 */
	void setResponseFormat(String value);

	/**
	 * Returns the value of the '<em><b>Request Params</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Request Params</em>' map.
	 * @see Y3886186.Y3886186Package#getApiResource_RequestParams()
	 * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry&lt;org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString&gt;"
	 * @generated
	 */
	EMap<String, String> getRequestParams();

	/**
	 * Returns the value of the '<em><b>Response Params</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Response Params</em>' map.
	 * @see Y3886186.Y3886186Package#getApiResource_ResponseParams()
	 * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry&lt;org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString&gt;"
	 * @generated
	 */
	EMap<String, String> getResponseParams();

	/**
	 * Returns the value of the '<em><b>Error Responses</b></em>' containment reference list.
	 * The list contents are of type {@link Y3886186.ApiError}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Error Responses</em>' containment reference list.
	 * @see Y3886186.Y3886186Package#getApiResource_ErrorResponses()
	 * @model containment="true"
	 * @generated
	 */
	EList<ApiError> getErrorResponses();

} // ApiResource
