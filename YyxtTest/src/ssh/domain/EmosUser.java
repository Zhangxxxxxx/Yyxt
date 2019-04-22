package ssh.domain;

import java.security.Timestamp;
import java.util.Date;

public class EmosUser {
	private String ID;//主键ID
	private String CPTROOMID;
	private String CPTROOMNAME;
	private String DEPTID;//部门id
	private String DEPTNAME;//部门名称
	private String EMAIL;//email
	private String FAMILYADDRESS;//家庭住址
	private String FAX;//传真
	private String MOBILE;//手机
	private String OPERUSERID;
	private String PHONE;//
	private String REMARK;//备注
	private String SEX;//性别
	private String USERDEGREE;//部门
	private String USERID;//用户id
	private String USERNAME;//用户姓名
	private String OPERREMOTIP;//
	private Date SAVETIME;//
	private Date UPDATETIME;//
	private String ACCOUNT_ENABLED;//
	private String ACCOUNT_EXPIRED;//
	private String ACCOUNT_LOCKED;//
	private String CREDENTIALS_EXPIRED;//
	private String PASSWORD;//密码
	private String POSTAL_CODE;
	private Integer DELETED;//
	private String ISFULLEMPLOY;//
	private String ISREST;//
	private String USERSTATUS;//
	private String PORTALROLENAME;//
	private Integer FLAG;//
	private Integer FAIL_COUNT;//
	private String ISPARTNERS;//
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getCPTROOMID() {
		return CPTROOMID;
	}
	public void setCPTROOMID(String cPTROOMID) {
		CPTROOMID = cPTROOMID;
	}
	public String getCPTROOMNAME() {
		return CPTROOMNAME;
	}
	public void setCPTROOMNAME(String cPTROOMNAME) {
		CPTROOMNAME = cPTROOMNAME;
	}
	public String getDEPTID() {
		return DEPTID;
	}
	public void setDEPTID(String dEPTID) {
		DEPTID = dEPTID;
	}
	public String getDEPTNAME() {
		return DEPTNAME;
	}
	public void setDEPTNAME(String dEPTNAME) {
		DEPTNAME = dEPTNAME;
	}
	public String getEMAIL() {
		return EMAIL;
	}
	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}
	public String getFAMILYADDRESS() {
		return FAMILYADDRESS;
	}
	public void setFAMILYADDRESS(String fAMILYADDRESS) {
		FAMILYADDRESS = fAMILYADDRESS;
	}
	public String getFAX() {
		return FAX;
	}
	public void setFAX(String fAX) {
		FAX = fAX;
	}
	public String getMOBILE() {
		return MOBILE;
	}
	public void setMOBILE(String mOBILE) {
		MOBILE = mOBILE;
	}
	public String getOPERUSERID() {
		return OPERUSERID;
	}
	public void setOPERUSERID(String oPERUSERID) {
		OPERUSERID = oPERUSERID;
	}
	public String getPHONE() {
		return PHONE;
	}
	public void setPHONE(String pHONE) {
		PHONE = pHONE;
	}
	public String getREMARK() {
		return REMARK;
	}
	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}
	public String getSEX() {
		return SEX;
	}
	public void setSEX(String sEX) {
		SEX = sEX;
	}
	public String getUSERDEGREE() {
		return USERDEGREE;
	}
	public void setUSERDEGREE(String uSERDEGREE) {
		USERDEGREE = uSERDEGREE;
	}
	public String getUSERID() {
		return USERID;
	}
	public void setUSERID(String uSERID) {
		USERID = uSERID;
	}
	public String getUSERNAME() {
		return USERNAME;
	}
	public void setUSERNAME(String uSERNAME) {
		USERNAME = uSERNAME;
	}
	public String getOPERREMOTIP() {
		return OPERREMOTIP;
	}
	public void setOPERREMOTIP(String oPERREMOTIP) {
		OPERREMOTIP = oPERREMOTIP;
	}
	public Date getSAVETIME() {
		return SAVETIME;
	}
	public void setSAVETIME(Date sAVETIME) {
		SAVETIME = sAVETIME;
	}
	public Date getUPDATETIME() {
		return UPDATETIME;
	}
	public void setUPDATETIME(Date uPDATETIME) {
		UPDATETIME = uPDATETIME;
	}
	public String getACCOUNT_ENABLED() {
		return ACCOUNT_ENABLED;
	}
	public void setACCOUNT_ENABLED(String aCCOUNT_ENABLED) {
		ACCOUNT_ENABLED = aCCOUNT_ENABLED;
	}
	public String getACCOUNT_EXPIRED() {
		return ACCOUNT_EXPIRED;
	}
	public void setACCOUNT_EXPIRED(String aCCOUNT_EXPIRED) {
		ACCOUNT_EXPIRED = aCCOUNT_EXPIRED;
	}
	public String getACCOUNT_LOCKED() {
		return ACCOUNT_LOCKED;
	}
	public void setACCOUNT_LOCKED(String aCCOUNT_LOCKED) {
		ACCOUNT_LOCKED = aCCOUNT_LOCKED;
	}
	public String getCREDENTIALS_EXPIRED() {
		return CREDENTIALS_EXPIRED;
	}
	public void setCREDENTIALS_EXPIRED(String cREDENTIALS_EXPIRED) {
		CREDENTIALS_EXPIRED = cREDENTIALS_EXPIRED;
	}
	public String getPASSWORD() {
		return PASSWORD;
	}
	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}
	public String getPOSTAL_CODE() {
		return POSTAL_CODE;
	}
	public void setPOSTAL_CODE(String pOSTAL_CODE) {
		POSTAL_CODE = pOSTAL_CODE;
	}
	public Integer getDELETED() {
		return DELETED;
	}
	public void setDELETED(Integer dELETED) {
		DELETED = dELETED;
	}
	public String getISFULLEMPLOY() {
		return ISFULLEMPLOY;
	}
	public void setISFULLEMPLOY(String iSFULLEMPLOY) {
		ISFULLEMPLOY = iSFULLEMPLOY;
	}
	public String getISREST() {
		return ISREST;
	}
	public void setISREST(String iSREST) {
		ISREST = iSREST;
	}
	public String getUSERSTATUS() {
		return USERSTATUS;
	}
	public void setUSERSTATUS(String uSERSTATUS) {
		USERSTATUS = uSERSTATUS;
	}
	public String getPORTALROLENAME() {
		return PORTALROLENAME;
	}
	public void setPORTALROLENAME(String pORTALROLENAME) {
		PORTALROLENAME = pORTALROLENAME;
	}
	public Integer getFLAG() {
		return FLAG;
	}
	public void setFLAG(Integer fLAG) {
		FLAG = fLAG;
	}
	public Integer getFAIL_COUNT() {
		return FAIL_COUNT;
	}
	public void setFAIL_COUNT(Integer fAIL_COUNT) {
		FAIL_COUNT = fAIL_COUNT;
	}
	public String getISPARTNERS() {
		return ISPARTNERS;
	}
	public void setISPARTNERS(String iSPARTNERS) {
		ISPARTNERS = iSPARTNERS;
	}
}
