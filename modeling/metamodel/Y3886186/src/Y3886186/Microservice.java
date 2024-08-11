/**
 */
package Y3886186;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Microservice</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link Y3886186.Microservice#getName <em>Name</em>}</li>
 *   <li>{@link Y3886186.Microservice#getDescription <em>Description</em>}</li>
 *   <li>{@link Y3886186.Microservice#getCommunicationPatterns <em>Communication Patterns</em>}</li>
 *   <li>{@link Y3886186.Microservice#getSubscribes <em>Subscribes</em>}</li>
 *   <li>{@link Y3886186.Microservice#getPublishes <em>Publishes</em>}</li>
 *   <li>{@link Y3886186.Microservice#getApiResources <em>Api Resources</em>}</li>
 *   <li>{@link Y3886186.Microservice#getContainerizatin <em>Containerizatin</em>}</li>
 * </ul>
 *
 * @see Y3886186.Y3886186Package#getMicroservice()
 * @model
 * @generated
 */
public interface Microservice extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see Y3886186.Y3886186Package#getMicroservice_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link Y3886186.Microservice#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see Y3886186.Y3886186Package#getMicroservice_Description()
	 * @model
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link Y3886186.Microservice#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Communication Patterns</b></em>' attribute list.
	 * The list contents are of type {@link Y3886186.CommunicationPattern}.
	 * The literals are from the enumeration {@link Y3886186.CommunicationPattern}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Communication Patterns</em>' attribute list.
	 * @see Y3886186.CommunicationPattern
	 * @see Y3886186.Y3886186Package#getMicroservice_CommunicationPatterns()
	 * @model
	 * @generated
	 */
	EList<CommunicationPattern> getCommunicationPatterns();

	/**
	 * Returns the value of the '<em><b>Subscribes</b></em>' reference list.
	 * The list contents are of type {@link Y3886186.EventStream}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Subscribes</em>' reference list.
	 * @see Y3886186.Y3886186Package#getMicroservice_Subscribes()
	 * @model
	 * @generated
	 */
	EList<EventStream> getSubscribes();

	/**
	 * Returns the value of the '<em><b>Publishes</b></em>' reference list.
	 * The list contents are of type {@link Y3886186.EventStream}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Publishes</em>' reference list.
	 * @see Y3886186.Y3886186Package#getMicroservice_Publishes()
	 * @model
	 * @generated
	 */
	EList<EventStream> getPublishes();

	/**
	 * Returns the value of the '<em><b>Api Resources</b></em>' containment reference list.
	 * The list contents are of type {@link Y3886186.ApiResource}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Api Resources</em>' containment reference list.
	 * @see Y3886186.Y3886186Package#getMicroservice_ApiResources()
	 * @model containment="true"
	 * @generated
	 */
	EList<ApiResource> getApiResources();

	/**
	 * Returns the value of the '<em><b>Containerizatin</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Containerizatin</em>' containment reference.
	 * @see #setContainerizatin(Containerization)
	 * @see Y3886186.Y3886186Package#getMicroservice_Containerizatin()
	 * @model containment="true"
	 * @generated
	 */
	Containerization getContainerizatin();

	/**
	 * Sets the value of the '{@link Y3886186.Microservice#getContainerizatin <em>Containerizatin</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Containerizatin</em>' containment reference.
	 * @see #getContainerizatin()
	 * @generated
	 */
	void setContainerizatin(Containerization value);

} // Microservice
