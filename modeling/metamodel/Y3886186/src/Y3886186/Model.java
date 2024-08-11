/**
 */
package Y3886186;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link Y3886186.Model#getMicroservices <em>Microservices</em>}</li>
 *   <li>{@link Y3886186.Model#getEventStreams <em>Event Streams</em>}</li>
 *   <li>{@link Y3886186.Model#getEvents <em>Events</em>}</li>
 * </ul>
 *
 * @see Y3886186.Y3886186Package#getModel()
 * @model
 * @generated
 */
public interface Model extends EObject {
	/**
	 * Returns the value of the '<em><b>Microservices</b></em>' containment reference list.
	 * The list contents are of type {@link Y3886186.Microservice}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Microservices</em>' containment reference list.
	 * @see Y3886186.Y3886186Package#getModel_Microservices()
	 * @model containment="true"
	 * @generated
	 */
	EList<Microservice> getMicroservices();

	/**
	 * Returns the value of the '<em><b>Event Streams</b></em>' containment reference list.
	 * The list contents are of type {@link Y3886186.EventStream}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Event Streams</em>' containment reference list.
	 * @see Y3886186.Y3886186Package#getModel_EventStreams()
	 * @model containment="true"
	 * @generated
	 */
	EList<EventStream> getEventStreams();

	/**
	 * Returns the value of the '<em><b>Events</b></em>' containment reference list.
	 * The list contents are of type {@link Y3886186.Event}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Events</em>' containment reference list.
	 * @see Y3886186.Y3886186Package#getModel_Events()
	 * @model containment="true"
	 * @generated
	 */
	EList<Event> getEvents();

} // Model
