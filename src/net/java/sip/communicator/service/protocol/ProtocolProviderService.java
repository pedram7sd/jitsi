/*
 * SIP Communicator, the OpenSource Java VoIP and Instant Messaging client.
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package net.java.sip.communicator.service.protocol;

import net.java.sip.communicator.service.protocol.event.*;
import java.util.*;


/**
 * The ProtocolProvider interface should be implemented by bundles that wrap
 * Instant Messaging and telephony protocol stacks. It gives the user interface
 * a way to plug into these stacks and receive notifications on status change
 * and incoming calls, as well as deliver user requests for establishing or
 * ending calls, putting participants on hold and etc.
 * <p>
 * An instance of a ProtocolProviderService corresponds to a particular user
 * account and all operations performed through a provider (sending messages,
 * modifying contact lists, receiving calls)would pertain to this particular
 * user account.
 *<p>
 * ProtocolProviderService instances are created through the provider factory.
 * Each protocol provider is assigned a unique AccountID instance that uniquely
 * identifies it. Account id's for different accounts are guaranteed to be
 * different and in the same time the ID of a particular account against a given
 * service over any protocol will always be the same (so that we detect attempts
 * for creating the same account twice.)
 *
 * @author Emil Ivov
 * @see AccountID
 */
public interface ProtocolProviderService
{
    /**
     * Starts the registration process. Connection details such as
     * registration server, user name/number are provided through the
     * configuration service through implementation specific properties.
     *
     * @param authority the security authority that will be used for resolving
     *        any security challenges that may be returned during the
     *        registration or at any moment while wer're registered.
     *
     */
    public void register(SecurityAuthority authority);

    /**
     * Ends the registration of this protocol provider with the current
     * registration service.
     */
    public void unregister();

    /**
     * Indicates whether or not this provider is registered
     * @return true if the provider is currently registered and false otherwise.
     */
    public boolean isRegistered();

    /**
     * Returns the state of the registration of this protocol provider with the
     * corresponding registration service.
     * @return ProviderRegistrationState
     */
    public RegistrationState getRegistrationState();

    /**
     * Returns the short name of the protocol that the implementation of this
     * provider is based upon (like SIP, Jabber, ICQ/AIM,  or others for
     * example). If the name of the protocol has been enumerated in
     * ProtocolNames then the value returned by this method must be the same as
     * the one in ProtocolNames.
     * @return a String containing the short name of the protocol this service
     * is implementing (most often that would be a name in ProtocolNames).
     */
    public String getProtocolName();

    /**
     * Registers the specified listener with this provider so that it would
     * receive notifications on changes of its state or other properties such
     * as its local address and display name.
     * @param listener the listener to register.
     */
    public void addRegistrationStateChangeListener(
        RegistrationStateChangeListener listener);

    /**
     * Removes the specified listener.
     * @param listener the listener to remove.
     */
    public void removeRegistrationStateChangeListener(
        RegistrationStateChangeListener listener);

    /**
     * Returns an array containing all operation sets supported by the current
     * implementation. When querying this method users must be prepared to
     * receive any sybset of the OperationSet-s defined by this service. They
     * MUST ignore any OperationSet-s that they are not aware of and that may be
     * defined by future version of this service. Such "unknown" OperationSet-s
     * though not encouraged, may also be defined by service implementors.
     *
     * @return a java.util.Map containing instance of all supported operation
     * sets mapped against their class names (e.g.
     * OperationSetPresence.class.getName()) .
     */
    public Map getSupportedOperationSets();

    /**
     * Makes the service implementation close all open sockets and release
     * any resources that it might have taken and prepare for shutdown/garbage
     * collection.
     */
    public void shutdown();

    /**
     * A hashcode allowing usage of protocol providers as keys in Hashtables.
     * @return an int that may be used when storing protocol providers as
     * hashtable keys.
     */
    public int hashCode();

    /**
     * Returns the AccountID that uniquely identifies the account represented by
     * this instance of the ProtocolProviderService.
     * @return the id of the account represented by this provider.
     */
    public AccountID getAccountID();
}
