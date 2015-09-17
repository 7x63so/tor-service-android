/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /Users/k/dev-ws/real-service/tor-service-android/app/src/main/aidl/xyz/gwh/tor/ITorService.aidl
 */
package xyz.gwh.tor;
public interface ITorService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements xyz.gwh.tor.ITorService
{
private static final java.lang.String DESCRIPTOR = "xyz.gwh.tor.ITorService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an xyz.gwh.tor.ITorService interface,
 * generating a proxy if needed.
 */
public static xyz.gwh.tor.ITorService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof xyz.gwh.tor.ITorService))) {
return ((xyz.gwh.tor.ITorService)iin);
}
return new xyz.gwh.tor.ITorService.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_setConfig:
{
data.enforceInterface(DESCRIPTOR);
xyz.gwh.tor.config.Torrc _arg0;
if ((0!=data.readInt())) {
_arg0 = xyz.gwh.tor.config.Torrc.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.setConfig(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_exit:
{
data.enforceInterface(DESCRIPTOR);
this.exit();
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements xyz.gwh.tor.ITorService
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public void setConfig(xyz.gwh.tor.config.Torrc config) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((config!=null)) {
_data.writeInt(1);
config.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_setConfig, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void exit() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_exit, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_setConfig = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_exit = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
}
public void setConfig(xyz.gwh.tor.config.Torrc config) throws android.os.RemoteException;
public void exit() throws android.os.RemoteException;
}
