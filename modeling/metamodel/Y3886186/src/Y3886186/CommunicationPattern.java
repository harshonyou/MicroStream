/**
 */
package Y3886186;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Communication Pattern</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see Y3886186.Y3886186Package#getCommunicationPattern()
 * @model
 * @generated
 */
public enum CommunicationPattern implements Enumerator {
	/**
	 * The '<em><b>REST</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #REST_VALUE
	 * @generated
	 * @ordered
	 */
	REST(0, "REST", "REST"),

	/**
	 * The '<em><b>MESSAGE QUEUE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MESSAGE_QUEUE_VALUE
	 * @generated
	 * @ordered
	 */
	MESSAGE_QUEUE(1, "MESSAGE_QUEUE", "MESSAGE_QUEUE"),

	/**
	 * The '<em><b>GRPC</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GRPC_VALUE
	 * @generated
	 * @ordered
	 */
	GRPC(2, "GRPC", "GRPC"),

	/**
	 * The '<em><b>WEBSOCKET</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #WEBSOCKET_VALUE
	 * @generated
	 * @ordered
	 */
	WEBSOCKET(3, "WEBSOCKET", "WEBSOCKET");

	/**
	 * The '<em><b>REST</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #REST
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int REST_VALUE = 0;

	/**
	 * The '<em><b>MESSAGE QUEUE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MESSAGE_QUEUE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int MESSAGE_QUEUE_VALUE = 1;

	/**
	 * The '<em><b>GRPC</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GRPC
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int GRPC_VALUE = 2;

	/**
	 * The '<em><b>WEBSOCKET</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #WEBSOCKET
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int WEBSOCKET_VALUE = 3;

	/**
	 * An array of all the '<em><b>Communication Pattern</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final CommunicationPattern[] VALUES_ARRAY =
		new CommunicationPattern[] {
			REST,
			MESSAGE_QUEUE,
			GRPC,
			WEBSOCKET,
		};

	/**
	 * A public read-only list of all the '<em><b>Communication Pattern</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<CommunicationPattern> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Communication Pattern</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static CommunicationPattern get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			CommunicationPattern result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Communication Pattern</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static CommunicationPattern getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			CommunicationPattern result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Communication Pattern</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static CommunicationPattern get(int value) {
		switch (value) {
			case REST_VALUE: return REST;
			case MESSAGE_QUEUE_VALUE: return MESSAGE_QUEUE;
			case GRPC_VALUE: return GRPC;
			case WEBSOCKET_VALUE: return WEBSOCKET;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private CommunicationPattern(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
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
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //CommunicationPattern
