/**
 */
package Y3886186.impl;

import Y3886186.Containerization;
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

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Containerization</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link Y3886186.impl.ContainerizationImpl#getTechnology <em>Technology</em>}</li>
 *   <li>{@link Y3886186.impl.ContainerizationImpl#getContainerConfigs <em>Container Configs</em>}</li>
 *   <li>{@link Y3886186.impl.ContainerizationImpl#getEnvironmentVariables <em>Environment Variables</em>}</li>
 *   <li>{@link Y3886186.impl.ContainerizationImpl#getDependsOn <em>Depends On</em>}</li>
 *   <li>{@link Y3886186.impl.ContainerizationImpl#getNetworks <em>Networks</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ContainerizationImpl extends MinimalEObjectImpl.Container implements Containerization {
	/**
	 * The default value of the '{@link #getTechnology() <em>Technology</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTechnology()
	 * @generated
	 * @ordered
	 */
	protected static final String TECHNOLOGY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTechnology() <em>Technology</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTechnology()
	 * @generated
	 * @ordered
	 */
	protected String technology = TECHNOLOGY_EDEFAULT;

	/**
	 * The cached value of the '{@link #getContainerConfigs() <em>Container Configs</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContainerConfigs()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> containerConfigs;

	/**
	 * The cached value of the '{@link #getEnvironmentVariables() <em>Environment Variables</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnvironmentVariables()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> environmentVariables;

	/**
	 * The cached value of the '{@link #getDependsOn() <em>Depends On</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDependsOn()
	 * @generated
	 * @ordered
	 */
	protected EList<String> dependsOn;

	/**
	 * The cached value of the '{@link #getNetworks() <em>Networks</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNetworks()
	 * @generated
	 * @ordered
	 */
	protected EList<String> networks;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ContainerizationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Y3886186Package.Literals.CONTAINERIZATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTechnology() {
		return technology;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTechnology(String newTechnology) {
		String oldTechnology = technology;
		technology = newTechnology;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Y3886186Package.CONTAINERIZATION__TECHNOLOGY, oldTechnology, technology));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, String> getContainerConfigs() {
		if (containerConfigs == null) {
			containerConfigs = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, Y3886186Package.CONTAINERIZATION__CONTAINER_CONFIGS);
		}
		return containerConfigs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, String> getEnvironmentVariables() {
		if (environmentVariables == null) {
			environmentVariables = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, Y3886186Package.CONTAINERIZATION__ENVIRONMENT_VARIABLES);
		}
		return environmentVariables;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getDependsOn() {
		if (dependsOn == null) {
			dependsOn = new EDataTypeUniqueEList<String>(String.class, this, Y3886186Package.CONTAINERIZATION__DEPENDS_ON);
		}
		return dependsOn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getNetworks() {
		if (networks == null) {
			networks = new EDataTypeUniqueEList<String>(String.class, this, Y3886186Package.CONTAINERIZATION__NETWORKS);
		}
		return networks;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Y3886186Package.CONTAINERIZATION__CONTAINER_CONFIGS:
				return ((InternalEList<?>)getContainerConfigs()).basicRemove(otherEnd, msgs);
			case Y3886186Package.CONTAINERIZATION__ENVIRONMENT_VARIABLES:
				return ((InternalEList<?>)getEnvironmentVariables()).basicRemove(otherEnd, msgs);
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
			case Y3886186Package.CONTAINERIZATION__TECHNOLOGY:
				return getTechnology();
			case Y3886186Package.CONTAINERIZATION__CONTAINER_CONFIGS:
				if (coreType) return getContainerConfigs();
				else return getContainerConfigs().map();
			case Y3886186Package.CONTAINERIZATION__ENVIRONMENT_VARIABLES:
				if (coreType) return getEnvironmentVariables();
				else return getEnvironmentVariables().map();
			case Y3886186Package.CONTAINERIZATION__DEPENDS_ON:
				return getDependsOn();
			case Y3886186Package.CONTAINERIZATION__NETWORKS:
				return getNetworks();
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
			case Y3886186Package.CONTAINERIZATION__TECHNOLOGY:
				setTechnology((String)newValue);
				return;
			case Y3886186Package.CONTAINERIZATION__CONTAINER_CONFIGS:
				((EStructuralFeature.Setting)getContainerConfigs()).set(newValue);
				return;
			case Y3886186Package.CONTAINERIZATION__ENVIRONMENT_VARIABLES:
				((EStructuralFeature.Setting)getEnvironmentVariables()).set(newValue);
				return;
			case Y3886186Package.CONTAINERIZATION__DEPENDS_ON:
				getDependsOn().clear();
				getDependsOn().addAll((Collection<? extends String>)newValue);
				return;
			case Y3886186Package.CONTAINERIZATION__NETWORKS:
				getNetworks().clear();
				getNetworks().addAll((Collection<? extends String>)newValue);
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
			case Y3886186Package.CONTAINERIZATION__TECHNOLOGY:
				setTechnology(TECHNOLOGY_EDEFAULT);
				return;
			case Y3886186Package.CONTAINERIZATION__CONTAINER_CONFIGS:
				getContainerConfigs().clear();
				return;
			case Y3886186Package.CONTAINERIZATION__ENVIRONMENT_VARIABLES:
				getEnvironmentVariables().clear();
				return;
			case Y3886186Package.CONTAINERIZATION__DEPENDS_ON:
				getDependsOn().clear();
				return;
			case Y3886186Package.CONTAINERIZATION__NETWORKS:
				getNetworks().clear();
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
			case Y3886186Package.CONTAINERIZATION__TECHNOLOGY:
				return TECHNOLOGY_EDEFAULT == null ? technology != null : !TECHNOLOGY_EDEFAULT.equals(technology);
			case Y3886186Package.CONTAINERIZATION__CONTAINER_CONFIGS:
				return containerConfigs != null && !containerConfigs.isEmpty();
			case Y3886186Package.CONTAINERIZATION__ENVIRONMENT_VARIABLES:
				return environmentVariables != null && !environmentVariables.isEmpty();
			case Y3886186Package.CONTAINERIZATION__DEPENDS_ON:
				return dependsOn != null && !dependsOn.isEmpty();
			case Y3886186Package.CONTAINERIZATION__NETWORKS:
				return networks != null && !networks.isEmpty();
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
		result.append(" (technology: ");
		result.append(technology);
		result.append(", dependsOn: ");
		result.append(dependsOn);
		result.append(", networks: ");
		result.append(networks);
		result.append(')');
		return result.toString();
	}

} //ContainerizationImpl
