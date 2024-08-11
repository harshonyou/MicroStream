/**
 */
package Y3886186.impl;

import Y3886186.ApiResource;
import Y3886186.CommunicationPattern;
import Y3886186.Containerization;
import Y3886186.EventStream;
import Y3886186.Microservice;
import Y3886186.Y3886186Package;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Microservice</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link Y3886186.impl.MicroserviceImpl#getName <em>Name</em>}</li>
 *   <li>{@link Y3886186.impl.MicroserviceImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link Y3886186.impl.MicroserviceImpl#getCommunicationPatterns <em>Communication Patterns</em>}</li>
 *   <li>{@link Y3886186.impl.MicroserviceImpl#getSubscribes <em>Subscribes</em>}</li>
 *   <li>{@link Y3886186.impl.MicroserviceImpl#getPublishes <em>Publishes</em>}</li>
 *   <li>{@link Y3886186.impl.MicroserviceImpl#getApiResources <em>Api Resources</em>}</li>
 *   <li>{@link Y3886186.impl.MicroserviceImpl#getContainerizatin <em>Containerizatin</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MicroserviceImpl extends MinimalEObjectImpl.Container implements Microservice {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected String description = DESCRIPTION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getCommunicationPatterns() <em>Communication Patterns</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCommunicationPatterns()
	 * @generated
	 * @ordered
	 */
	protected EList<CommunicationPattern> communicationPatterns;

	/**
	 * The cached value of the '{@link #getSubscribes() <em>Subscribes</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubscribes()
	 * @generated
	 * @ordered
	 */
	protected EList<EventStream> subscribes;

	/**
	 * The cached value of the '{@link #getPublishes() <em>Publishes</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPublishes()
	 * @generated
	 * @ordered
	 */
	protected EList<EventStream> publishes;

	/**
	 * The cached value of the '{@link #getApiResources() <em>Api Resources</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getApiResources()
	 * @generated
	 * @ordered
	 */
	protected EList<ApiResource> apiResources;

	/**
	 * The cached value of the '{@link #getContainerizatin() <em>Containerizatin</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContainerizatin()
	 * @generated
	 * @ordered
	 */
	protected Containerization containerizatin;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MicroserviceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Y3886186Package.Literals.MICROSERVICE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Y3886186Package.MICROSERVICE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Y3886186Package.MICROSERVICE__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<CommunicationPattern> getCommunicationPatterns() {
		if (communicationPatterns == null) {
			communicationPatterns = new EDataTypeUniqueEList<CommunicationPattern>(CommunicationPattern.class, this, Y3886186Package.MICROSERVICE__COMMUNICATION_PATTERNS);
		}
		return communicationPatterns;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EventStream> getSubscribes() {
		if (subscribes == null) {
			subscribes = new EObjectResolvingEList<EventStream>(EventStream.class, this, Y3886186Package.MICROSERVICE__SUBSCRIBES);
		}
		return subscribes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EventStream> getPublishes() {
		if (publishes == null) {
			publishes = new EObjectResolvingEList<EventStream>(EventStream.class, this, Y3886186Package.MICROSERVICE__PUBLISHES);
		}
		return publishes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ApiResource> getApiResources() {
		if (apiResources == null) {
			apiResources = new EObjectContainmentEList<ApiResource>(ApiResource.class, this, Y3886186Package.MICROSERVICE__API_RESOURCES);
		}
		return apiResources;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Containerization getContainerizatin() {
		return containerizatin;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetContainerizatin(Containerization newContainerizatin, NotificationChain msgs) {
		Containerization oldContainerizatin = containerizatin;
		containerizatin = newContainerizatin;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Y3886186Package.MICROSERVICE__CONTAINERIZATIN, oldContainerizatin, newContainerizatin);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setContainerizatin(Containerization newContainerizatin) {
		if (newContainerizatin != containerizatin) {
			NotificationChain msgs = null;
			if (containerizatin != null)
				msgs = ((InternalEObject)containerizatin).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Y3886186Package.MICROSERVICE__CONTAINERIZATIN, null, msgs);
			if (newContainerizatin != null)
				msgs = ((InternalEObject)newContainerizatin).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Y3886186Package.MICROSERVICE__CONTAINERIZATIN, null, msgs);
			msgs = basicSetContainerizatin(newContainerizatin, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Y3886186Package.MICROSERVICE__CONTAINERIZATIN, newContainerizatin, newContainerizatin));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Y3886186Package.MICROSERVICE__API_RESOURCES:
				return ((InternalEList<?>)getApiResources()).basicRemove(otherEnd, msgs);
			case Y3886186Package.MICROSERVICE__CONTAINERIZATIN:
				return basicSetContainerizatin(null, msgs);
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
			case Y3886186Package.MICROSERVICE__NAME:
				return getName();
			case Y3886186Package.MICROSERVICE__DESCRIPTION:
				return getDescription();
			case Y3886186Package.MICROSERVICE__COMMUNICATION_PATTERNS:
				return getCommunicationPatterns();
			case Y3886186Package.MICROSERVICE__SUBSCRIBES:
				return getSubscribes();
			case Y3886186Package.MICROSERVICE__PUBLISHES:
				return getPublishes();
			case Y3886186Package.MICROSERVICE__API_RESOURCES:
				return getApiResources();
			case Y3886186Package.MICROSERVICE__CONTAINERIZATIN:
				return getContainerizatin();
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
			case Y3886186Package.MICROSERVICE__NAME:
				setName((String)newValue);
				return;
			case Y3886186Package.MICROSERVICE__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case Y3886186Package.MICROSERVICE__COMMUNICATION_PATTERNS:
				getCommunicationPatterns().clear();
				getCommunicationPatterns().addAll((Collection<? extends CommunicationPattern>)newValue);
				return;
			case Y3886186Package.MICROSERVICE__SUBSCRIBES:
				getSubscribes().clear();
				getSubscribes().addAll((Collection<? extends EventStream>)newValue);
				return;
			case Y3886186Package.MICROSERVICE__PUBLISHES:
				getPublishes().clear();
				getPublishes().addAll((Collection<? extends EventStream>)newValue);
				return;
			case Y3886186Package.MICROSERVICE__API_RESOURCES:
				getApiResources().clear();
				getApiResources().addAll((Collection<? extends ApiResource>)newValue);
				return;
			case Y3886186Package.MICROSERVICE__CONTAINERIZATIN:
				setContainerizatin((Containerization)newValue);
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
			case Y3886186Package.MICROSERVICE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case Y3886186Package.MICROSERVICE__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case Y3886186Package.MICROSERVICE__COMMUNICATION_PATTERNS:
				getCommunicationPatterns().clear();
				return;
			case Y3886186Package.MICROSERVICE__SUBSCRIBES:
				getSubscribes().clear();
				return;
			case Y3886186Package.MICROSERVICE__PUBLISHES:
				getPublishes().clear();
				return;
			case Y3886186Package.MICROSERVICE__API_RESOURCES:
				getApiResources().clear();
				return;
			case Y3886186Package.MICROSERVICE__CONTAINERIZATIN:
				setContainerizatin((Containerization)null);
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
			case Y3886186Package.MICROSERVICE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case Y3886186Package.MICROSERVICE__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case Y3886186Package.MICROSERVICE__COMMUNICATION_PATTERNS:
				return communicationPatterns != null && !communicationPatterns.isEmpty();
			case Y3886186Package.MICROSERVICE__SUBSCRIBES:
				return subscribes != null && !subscribes.isEmpty();
			case Y3886186Package.MICROSERVICE__PUBLISHES:
				return publishes != null && !publishes.isEmpty();
			case Y3886186Package.MICROSERVICE__API_RESOURCES:
				return apiResources != null && !apiResources.isEmpty();
			case Y3886186Package.MICROSERVICE__CONTAINERIZATIN:
				return containerizatin != null;
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
		result.append(" (name: ");
		result.append(name);
		result.append(", description: ");
		result.append(description);
		result.append(", communicationPatterns: ");
		result.append(communicationPatterns);
		result.append(')');
		return result.toString();
	}

} //MicroserviceImpl
