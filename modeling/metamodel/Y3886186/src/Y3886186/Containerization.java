/**
 */
package Y3886186;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Containerization</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link Y3886186.Containerization#getTechnology <em>Technology</em>}</li>
 *   <li>{@link Y3886186.Containerization#getContainerConfigs <em>Container Configs</em>}</li>
 *   <li>{@link Y3886186.Containerization#getEnvironmentVariables <em>Environment Variables</em>}</li>
 *   <li>{@link Y3886186.Containerization#getDependsOn <em>Depends On</em>}</li>
 *   <li>{@link Y3886186.Containerization#getNetworks <em>Networks</em>}</li>
 * </ul>
 *
 * @see Y3886186.Y3886186Package#getContainerization()
 * @model
 * @generated
 */
public interface Containerization extends EObject {
	/**
	 * Returns the value of the '<em><b>Technology</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Technology</em>' attribute.
	 * @see #setTechnology(String)
	 * @see Y3886186.Y3886186Package#getContainerization_Technology()
	 * @model
	 * @generated
	 */
	String getTechnology();

	/**
	 * Sets the value of the '{@link Y3886186.Containerization#getTechnology <em>Technology</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Technology</em>' attribute.
	 * @see #getTechnology()
	 * @generated
	 */
	void setTechnology(String value);

	/**
	 * Returns the value of the '<em><b>Container Configs</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Container Configs</em>' map.
	 * @see Y3886186.Y3886186Package#getContainerization_ContainerConfigs()
	 * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry&lt;org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString&gt;"
	 * @generated
	 */
	EMap<String, String> getContainerConfigs();

	/**
	 * Returns the value of the '<em><b>Environment Variables</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Environment Variables</em>' map.
	 * @see Y3886186.Y3886186Package#getContainerization_EnvironmentVariables()
	 * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry&lt;org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString&gt;"
	 * @generated
	 */
	EMap<String, String> getEnvironmentVariables();

	/**
	 * Returns the value of the '<em><b>Depends On</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Depends On</em>' attribute list.
	 * @see Y3886186.Y3886186Package#getContainerization_DependsOn()
	 * @model
	 * @generated
	 */
	EList<String> getDependsOn();

	/**
	 * Returns the value of the '<em><b>Networks</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Networks</em>' attribute list.
	 * @see Y3886186.Y3886186Package#getContainerization_Networks()
	 * @model
	 * @generated
	 */
	EList<String> getNetworks();

} // Containerization
