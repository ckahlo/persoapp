
package de.bund.bsi.ecard.api._1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;
import iso.std.iso_iec._24727.tech.schema.ConnectionHandleType;
import iso.std.iso_iec._24727.tech.schema.RequestType;
import iso.std.iso_iec._24727.tech.schema.ResponseType;
import oasis.names.tc.dss._1_0.core.schema.RequestBaseType;
import oasis.names.tc.dss._1_0.core.schema.ResponseBaseType;
import oasis.names.tc.dss._1_0.core.schema.UseVerificationTimeType;
import org.etsi.uri._01903.v1_3.DigestAlgAndValueType;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the de.bund.bsi.ecard.api._1 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AddTrustedCertificateResponse_QNAME = new QName("http://www.bsi.bund.de/ecard/api/1.1", "AddTrustedCertificateResponse");
    private final static QName _SetCardInfoListResponse_QNAME = new QName("http://www.bsi.bund.de/ecard/api/1.1", "SetCardInfoListResponse");
    private final static QName _SetOCSPServicesResponse_QNAME = new QName("http://www.bsi.bund.de/ecard/api/1.1", "SetOCSPServicesResponse");
    private final static QName _DeleteCardInfoFilesResponse_QNAME = new QName("http://www.bsi.bund.de/ecard/api/1.1", "DeleteCardInfoFilesResponse");
    private final static QName _SetTrustedViewerConfigurationResponse_QNAME = new QName("http://www.bsi.bund.de/ecard/api/1.1", "SetTrustedViewerConfigurationResponse");
    private final static QName _DSIName_QNAME = new QName("http://www.bsi.bund.de/ecard/api/1.1", "DSIName");
    private final static QName _ConnectionHandle_QNAME = new QName("http://www.bsi.bund.de/ecard/api/1.1", "ConnectionHandle");
    private final static QName _PreviousTimeStampHash_QNAME = new QName("http://www.bsi.bund.de/ecard/api/1.1", "PreviousTimeStampHash");
    private final static QName _ShowViewerResponse_QNAME = new QName("http://www.bsi.bund.de/ecard/api/1.1", "ShowViewerResponse");
    private final static QName _AddCertificateResponse_QNAME = new QName("http://www.bsi.bund.de/ecard/api/1.1", "AddCertificateResponse");
    private final static QName _VerifyManifests_QNAME = new QName("http://www.bsi.bund.de/ecard/api/1.1", "VerifyManifests");
    private final static QName _RegisterIFDResponse_QNAME = new QName("http://www.bsi.bund.de/ecard/api/1.1", "RegisterIFDResponse");
    private final static QName _DeleteTSLResponse_QNAME = new QName("http://www.bsi.bund.de/ecard/api/1.1", "DeleteTSLResponse");
    private final static QName _IncludeEContent_QNAME = new QName("http://www.bsi.bund.de/ecard/api/1.1", "IncludeEContent");
    private final static QName _DeleteTrustedViewerResponse_QNAME = new QName("http://www.bsi.bund.de/ecard/api/1.1", "DeleteTrustedViewerResponse");
    private final static QName _SignatureForm_QNAME = new QName("http://www.bsi.bund.de/ecard/api/1.1", "SignatureForm");
    private final static QName _UseVerificationTime_QNAME = new QName("http://www.bsi.bund.de/ecard/api/1.1", "UseVerificationTime");
    private final static QName _APIACLModifyResponse_QNAME = new QName("http://www.bsi.bund.de/ecard/api/1.1", "APIACLModifyResponse");
    private final static QName _VerifyResponse_QNAME = new QName("http://www.bsi.bund.de/ecard/api/1.1", "VerifyResponse");
    private final static QName _EvidenceRecord_QNAME = new QName("http://www.bsi.bund.de/ecard/api/1.1", "EvidenceRecord");
    private final static QName _FrameworkUpdate_QNAME = new QName("http://www.bsi.bund.de/ecard/api/1.1", "FrameworkUpdate");
    private final static QName _SetDefaultParametersResponse_QNAME = new QName("http://www.bsi.bund.de/ecard/api/1.1", "SetDefaultParametersResponse");
    private final static QName _TerminateFramework_QNAME = new QName("http://www.bsi.bund.de/ecard/api/1.1", "TerminateFramework");
    private final static QName _AddTrustedViewerResponse_QNAME = new QName("http://www.bsi.bund.de/ecard/api/1.1", "AddTrustedViewerResponse");
    private final static QName _DIDName_QNAME = new QName("http://www.bsi.bund.de/ecard/api/1.1", "DIDName");
    private final static QName _SignRequest_QNAME = new QName("http://www.bsi.bund.de/ecard/api/1.1", "SignRequest");
    private final static QName _SetDirectoryServicesResponse_QNAME = new QName("http://www.bsi.bund.de/ecard/api/1.1", "SetDirectoryServicesResponse");
    private final static QName _TerminateFrameworkResponse_QNAME = new QName("http://www.bsi.bund.de/ecard/api/1.1", "TerminateFrameworkResponse");
    private final static QName _AddCardInfoFilesResponse_QNAME = new QName("http://www.bsi.bund.de/ecard/api/1.1", "AddCardInfoFilesResponse");
    private final static QName _SetTSServicesResponse_QNAME = new QName("http://www.bsi.bund.de/ecard/api/1.1", "SetTSServicesResponse");
    private final static QName _AddTSLResponse_QNAME = new QName("http://www.bsi.bund.de/ecard/api/1.1", "AddTSLResponse");
    private final static QName _FrameworkUpdateResponse_QNAME = new QName("http://www.bsi.bund.de/ecard/api/1.1", "FrameworkUpdateResponse");
    private final static QName _InitializeFramework_QNAME = new QName("http://www.bsi.bund.de/ecard/api/1.1", "InitializeFramework");
    private final static QName _DeleteCertificateResponse_QNAME = new QName("http://www.bsi.bund.de/ecard/api/1.1", "DeleteCertificateResponse");
    private final static QName _TrustedViewerInfo_QNAME = new QName("http://www.bsi.bund.de/ecard/api/1.1", "TrustedViewerInfo");
    private final static QName _UnregisterIFDResponse_QNAME = new QName("http://www.bsi.bund.de/ecard/api/1.1", "UnregisterIFDResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: de.bund.bsi.ecard.api._1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RegisterIFD }
     * 
     */
    public RegisterIFD createRegisterIFD() {
        return new RegisterIFD();
    }

    /**
     * Create an instance of {@link IFDConfigurationType }
     * 
     */
    public IFDConfigurationType createIFDConfigurationType() {
        return new IFDConfigurationType();
    }

    /**
     * Create an instance of {@link GetOCSPServices }
     * 
     */
    public GetOCSPServices createGetOCSPServices() {
        return new GetOCSPServices();
    }

    /**
     * Create an instance of {@link APISecurityConditionType }
     * 
     */
    public APISecurityConditionType createAPISecurityConditionType() {
        return new APISecurityConditionType();
    }

    /**
     * Create an instance of {@link ShowViewer }
     * 
     */
    public ShowViewer createShowViewer() {
        return new ShowViewer();
    }

    /**
     * Create an instance of {@link DeleteCardInfoFiles }
     * 
     */
    public DeleteCardInfoFiles createDeleteCardInfoFiles() {
        return new DeleteCardInfoFiles();
    }

    /**
     * Create an instance of {@link GetTSServices }
     * 
     */
    public GetTSServices createGetTSServices() {
        return new GetTSServices();
    }

    /**
     * Create an instance of {@link SignResponse }
     * 
     */
    public SignResponse createSignResponse() {
        return new SignResponse();
    }

    /**
     * Create an instance of {@link GetDefaultParametersResponse }
     * 
     */
    public GetDefaultParametersResponse createGetDefaultParametersResponse() {
        return new GetDefaultParametersResponse();
    }

    /**
     * Create an instance of {@link SetDefaultParameters }
     * 
     */
    public SetDefaultParameters createSetDefaultParameters() {
        return new SetDefaultParameters();
    }

    /**
     * Create an instance of {@link ExportTSLResponse }
     * 
     */
    public ExportTSLResponse createExportTSLResponse() {
        return new ExportTSLResponse();
    }

    /**
     * Create an instance of {@link UpdateModuleInfoType.File }
     * 
     */
    public UpdateModuleInfoType.File createUpdateModuleInfoTypeFile() {
        return new UpdateModuleInfoType.File();
    }

    /**
     * Create an instance of {@link GetCertificateResponse }
     * 
     */
    public GetCertificateResponse createGetCertificateResponse() {
        return new GetCertificateResponse();
    }

    /**
     * Create an instance of {@link TSServiceType }
     * 
     */
    public TSServiceType createTSServiceType() {
        return new TSServiceType();
    }

    /**
     * Create an instance of {@link DefaultParametersType.DefaultFrameworkBehaviour }
     * 
     */
    public DefaultParametersType.DefaultFrameworkBehaviour createDefaultParametersTypeDefaultFrameworkBehaviour() {
        return new DefaultParametersType.DefaultFrameworkBehaviour();
    }

    /**
     * Create an instance of {@link SetTSServices }
     * 
     */
    public SetTSServices createSetTSServices() {
        return new SetTSServices();
    }

    /**
     * Create an instance of {@link GetTSServicesResponse }
     * 
     */
    public GetTSServicesResponse createGetTSServicesResponse() {
        return new GetTSServicesResponse();
    }

    /**
     * Create an instance of {@link DeleteTrustedViewer }
     * 
     */
    public DeleteTrustedViewer createDeleteTrustedViewer() {
        return new DeleteTrustedViewer();
    }

    /**
     * Create an instance of {@link InitializeFrameworkResponse.Version }
     * 
     */
    public InitializeFrameworkResponse.Version createInitializeFrameworkResponseVersion() {
        return new InitializeFrameworkResponse.Version();
    }

    /**
     * Create an instance of {@link APIAuthenticationStateType }
     * 
     */
    public APIAuthenticationStateType createAPIAuthenticationStateType() {
        return new APIAuthenticationStateType();
    }

    /**
     * Create an instance of {@link SetTrustedViewerConfiguration }
     * 
     */
    public SetTrustedViewerConfiguration createSetTrustedViewerConfiguration() {
        return new SetTrustedViewerConfiguration();
    }

    /**
     * Create an instance of {@link GetCardInfoListResponse }
     * 
     */
    public GetCardInfoListResponse createGetCardInfoListResponse() {
        return new GetCardInfoListResponse();
    }

    /**
     * Create an instance of {@link CheckFrameworkUpdate }
     * 
     */
    public CheckFrameworkUpdate createCheckFrameworkUpdate() {
        return new CheckFrameworkUpdate();
    }

    /**
     * Create an instance of {@link GetTrustedIdentities }
     * 
     */
    public GetTrustedIdentities createGetTrustedIdentities() {
        return new GetTrustedIdentities();
    }

    /**
     * Create an instance of {@link ExportTSL }
     * 
     */
    public ExportTSL createExportTSL() {
        return new ExportTSL();
    }

    /**
     * Create an instance of {@link APIACLList }
     * 
     */
    public APIACLList createAPIACLList() {
        return new APIACLList();
    }

    /**
     * Create an instance of {@link SimpleEnrollmentInputType }
     * 
     */
    public SimpleEnrollmentInputType createSimpleEnrollmentInputType() {
        return new SimpleEnrollmentInputType();
    }

    /**
     * Create an instance of {@link DefaultParametersType.DefaultFrameworkBehaviour.VerifyAddedIdentity }
     * 
     */
    public DefaultParametersType.DefaultFrameworkBehaviour.VerifyAddedIdentity createDefaultParametersTypeDefaultFrameworkBehaviourVerifyAddedIdentity() {
        return new DefaultParametersType.DefaultFrameworkBehaviour.VerifyAddedIdentity();
    }

    /**
     * Create an instance of {@link ViewerConfigurationType }
     * 
     */
    public ViewerConfigurationType createViewerConfigurationType() {
        return new ViewerConfigurationType();
    }

    /**
     * Create an instance of {@link APIACLListResponse }
     * 
     */
    public APIACLListResponse createAPIACLListResponse() {
        return new APIACLListResponse();
    }

    /**
     * Create an instance of {@link AddTSL }
     * 
     */
    public AddTSL createAddTSL() {
        return new AddTSL();
    }

    /**
     * Create an instance of {@link LocalizedMessagesType }
     * 
     */
    public LocalizedMessagesType createLocalizedMessagesType() {
        return new LocalizedMessagesType();
    }

    /**
     * Create an instance of {@link GetOCSPServicesResponse }
     * 
     */
    public GetOCSPServicesResponse createGetOCSPServicesResponse() {
        return new GetOCSPServicesResponse();
    }

    /**
     * Create an instance of {@link InstalledModuleInfoType }
     * 
     */
    public InstalledModuleInfoType createInstalledModuleInfoType() {
        return new InstalledModuleInfoType();
    }

    /**
     * Create an instance of {@link AddTrustedViewer }
     * 
     */
    public AddTrustedViewer createAddTrustedViewer() {
        return new AddTrustedViewer();
    }

    /**
     * Create an instance of {@link ModuleInfoType }
     * 
     */
    public ModuleInfoType createModuleInfoType() {
        return new ModuleInfoType();
    }

    /**
     * Create an instance of {@link APIAccessControlListType }
     * 
     */
    public APIAccessControlListType createAPIAccessControlListType() {
        return new APIAccessControlListType();
    }

    /**
     * Create an instance of {@link DeleteCertificate }
     * 
     */
    public DeleteCertificate createDeleteCertificate() {
        return new DeleteCertificate();
    }

    /**
     * Create an instance of {@link APIAccessControlRuleType }
     * 
     */
    public APIAccessControlRuleType createAPIAccessControlRuleType() {
        return new APIAccessControlRuleType();
    }

    /**
     * Create an instance of {@link CheckFrameworkUpdateResponse }
     * 
     */
    public CheckFrameworkUpdateResponse createCheckFrameworkUpdateResponse() {
        return new CheckFrameworkUpdateResponse();
    }

    /**
     * Create an instance of {@link ShowViewer.ViewerMessage }
     * 
     */
    public ShowViewer.ViewerMessage createShowViewerViewerMessage() {
        return new ShowViewer.ViewerMessage();
    }

    /**
     * Create an instance of {@link TrustedViewerInfoType }
     * 
     */
    public TrustedViewerInfoType createTrustedViewerInfoType() {
        return new TrustedViewerInfoType();
    }

    /**
     * Create an instance of {@link GetTrustedViewerListResponse }
     * 
     */
    public GetTrustedViewerListResponse createGetTrustedViewerListResponse() {
        return new GetTrustedViewerListResponse();
    }

    /**
     * Create an instance of {@link ExportCertificateResponse }
     * 
     */
    public ExportCertificateResponse createExportCertificateResponse() {
        return new ExportCertificateResponse();
    }

    /**
     * Create an instance of {@link GetDefaultParameters }
     * 
     */
    public GetDefaultParameters createGetDefaultParameters() {
        return new GetDefaultParameters();
    }

    /**
     * Create an instance of {@link GetTrustedIdentitiesResponse }
     * 
     */
    public GetTrustedIdentitiesResponse createGetTrustedIdentitiesResponse() {
        return new GetTrustedIdentitiesResponse();
    }

    /**
     * Create an instance of {@link SetOCSPServices }
     * 
     */
    public SetOCSPServices createSetOCSPServices() {
        return new SetOCSPServices();
    }

    /**
     * Create an instance of {@link UnregisterIFD }
     * 
     */
    public UnregisterIFD createUnregisterIFD() {
        return new UnregisterIFD();
    }

    /**
     * Create an instance of {@link GetDirectoryServicesResponse }
     * 
     */
    public GetDirectoryServicesResponse createGetDirectoryServicesResponse() {
        return new GetDirectoryServicesResponse();
    }

    /**
     * Create an instance of {@link SimpleEnrollmentOutputType }
     * 
     */
    public SimpleEnrollmentOutputType createSimpleEnrollmentOutputType() {
        return new SimpleEnrollmentOutputType();
    }

    /**
     * Create an instance of {@link ViewerConfigurationType.SupportedDocumentTypes }
     * 
     */
    public ViewerConfigurationType.SupportedDocumentTypes createViewerConfigurationTypeSupportedDocumentTypes() {
        return new ViewerConfigurationType.SupportedDocumentTypes();
    }

    /**
     * Create an instance of {@link AddCertificate }
     * 
     */
    public AddCertificate createAddCertificate() {
        return new AddCertificate();
    }

    /**
     * Create an instance of {@link DefaultParametersType.UpdateService }
     * 
     */
    public DefaultParametersType.UpdateService createDefaultParametersTypeUpdateService() {
        return new DefaultParametersType.UpdateService();
    }

    /**
     * Create an instance of {@link VerifyRequest }
     * 
     */
    public VerifyRequest createVerifyRequest() {
        return new VerifyRequest();
    }

    /**
     * Create an instance of {@link StyleSheetType }
     * 
     */
    public StyleSheetType createStyleSheetType() {
        return new StyleSheetType();
    }

    /**
     * Create an instance of {@link DefaultParametersType.DefaultMessages }
     * 
     */
    public DefaultParametersType.DefaultMessages createDefaultParametersTypeDefaultMessages() {
        return new DefaultParametersType.DefaultMessages();
    }

    /**
     * Create an instance of {@link GetCertificate }
     * 
     */
    public GetCertificate createGetCertificate() {
        return new GetCertificate();
    }

    /**
     * Create an instance of {@link APISecurityConditionType.And }
     * 
     */
    public APISecurityConditionType.And createAPISecurityConditionTypeAnd() {
        return new APISecurityConditionType.And();
    }

    /**
     * Create an instance of {@link GetTrustedViewerConfiguration }
     * 
     */
    public GetTrustedViewerConfiguration createGetTrustedViewerConfiguration() {
        return new GetTrustedViewerConfiguration();
    }

    /**
     * Create an instance of {@link GetTrustedViewerConfigurationResponse }
     * 
     */
    public GetTrustedViewerConfigurationResponse createGetTrustedViewerConfigurationResponse() {
        return new GetTrustedViewerConfigurationResponse();
    }

    /**
     * Create an instance of {@link ExportCertificate }
     * 
     */
    public ExportCertificate createExportCertificate() {
        return new ExportCertificate();
    }

    /**
     * Create an instance of {@link APISecurityConditionType.Or }
     * 
     */
    public APISecurityConditionType.Or createAPISecurityConditionTypeOr() {
        return new APISecurityConditionType.Or();
    }

    /**
     * Create an instance of {@link DefaultParametersType }
     * 
     */
    public DefaultParametersType createDefaultParametersType() {
        return new DefaultParametersType();
    }

    /**
     * Create an instance of {@link SetCardInfoList }
     * 
     */
    public SetCardInfoList createSetCardInfoList() {
        return new SetCardInfoList();
    }

    /**
     * Create an instance of {@link Certificate }
     * 
     */
    public Certificate createCertificate() {
        return new Certificate();
    }

    /**
     * Create an instance of {@link AddTrustedCertificate }
     * 
     */
    public AddTrustedCertificate createAddTrustedCertificate() {
        return new AddTrustedCertificate();
    }

    /**
     * Create an instance of {@link EvidenceRecordType }
     * 
     */
    public EvidenceRecordType createEvidenceRecordType() {
        return new EvidenceRecordType();
    }

    /**
     * Create an instance of {@link GetTrustedViewerList }
     * 
     */
    public GetTrustedViewerList createGetTrustedViewerList() {
        return new GetTrustedViewerList();
    }

    /**
     * Create an instance of {@link SignOptionsType }
     * 
     */
    public SignOptionsType createSignOptionsType() {
        return new SignOptionsType();
    }

    /**
     * Create an instance of {@link GetDirectoryServices }
     * 
     */
    public GetDirectoryServices createGetDirectoryServices() {
        return new GetDirectoryServices();
    }

    /**
     * Create an instance of {@link DeleteTSL }
     * 
     */
    public DeleteTSL createDeleteTSL() {
        return new DeleteTSL();
    }

    /**
     * Create an instance of {@link SetDirectoryServices }
     * 
     */
    public SetDirectoryServices createSetDirectoryServices() {
        return new SetDirectoryServices();
    }

    /**
     * Create an instance of {@link APIACLModify }
     * 
     */
    public APIACLModify createAPIACLModify() {
        return new APIACLModify();
    }

    /**
     * Create an instance of {@link AddCardInfoFiles }
     * 
     */
    public AddCardInfoFiles createAddCardInfoFiles() {
        return new AddCardInfoFiles();
    }

    /**
     * Create an instance of {@link GetCardInfoList }
     * 
     */
    public GetCardInfoList createGetCardInfoList() {
        return new GetCardInfoList();
    }

    /**
     * Create an instance of {@link AddCertificateOptionsType }
     * 
     */
    public AddCertificateOptionsType createAddCertificateOptionsType() {
        return new AddCertificateOptionsType();
    }

    /**
     * Create an instance of {@link ServiceType }
     * 
     */
    public ServiceType createServiceType() {
        return new ServiceType();
    }

    /**
     * Create an instance of {@link TSLType }
     * 
     */
    public TSLType createTSLType() {
        return new TSLType();
    }

    /**
     * Create an instance of {@link APIAccessRuleType }
     * 
     */
    public APIAccessRuleType createAPIAccessRuleType() {
        return new APIAccessRuleType();
    }

    /**
     * Create an instance of {@link InitializeFrameworkResponse }
     * 
     */
    public InitializeFrameworkResponse createInitializeFrameworkResponse() {
        return new InitializeFrameworkResponse();
    }

    /**
     * Create an instance of {@link SignatureObject }
     * 
     */
    public SignatureObject createSignatureObject() {
        return new SignatureObject();
    }

    /**
     * Create an instance of {@link UpdateModuleInfoType }
     * 
     */
    public UpdateModuleInfoType createUpdateModuleInfoType() {
        return new UpdateModuleInfoType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bsi.bund.de/ecard/api/1.1", name = "AddTrustedCertificateResponse")
    public JAXBElement<ResponseType> createAddTrustedCertificateResponse(ResponseType value) {
        return new JAXBElement<ResponseType>(_AddTrustedCertificateResponse_QNAME, ResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bsi.bund.de/ecard/api/1.1", name = "SetCardInfoListResponse")
    public JAXBElement<ResponseType> createSetCardInfoListResponse(ResponseType value) {
        return new JAXBElement<ResponseType>(_SetCardInfoListResponse_QNAME, ResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bsi.bund.de/ecard/api/1.1", name = "SetOCSPServicesResponse")
    public JAXBElement<ResponseType> createSetOCSPServicesResponse(ResponseType value) {
        return new JAXBElement<ResponseType>(_SetOCSPServicesResponse_QNAME, ResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bsi.bund.de/ecard/api/1.1", name = "DeleteCardInfoFilesResponse")
    public JAXBElement<ResponseType> createDeleteCardInfoFilesResponse(ResponseType value) {
        return new JAXBElement<ResponseType>(_DeleteCardInfoFilesResponse_QNAME, ResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bsi.bund.de/ecard/api/1.1", name = "SetTrustedViewerConfigurationResponse")
    public JAXBElement<ResponseType> createSetTrustedViewerConfigurationResponse(ResponseType value) {
        return new JAXBElement<ResponseType>(_SetTrustedViewerConfigurationResponse_QNAME, ResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bsi.bund.de/ecard/api/1.1", name = "DSIName")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createDSIName(String value) {
        return new JAXBElement<String>(_DSIName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConnectionHandleType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bsi.bund.de/ecard/api/1.1", name = "ConnectionHandle")
    public JAXBElement<ConnectionHandleType> createConnectionHandle(ConnectionHandleType value) {
        return new JAXBElement<ConnectionHandleType>(_ConnectionHandle_QNAME, ConnectionHandleType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DigestAlgAndValueType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bsi.bund.de/ecard/api/1.1", name = "PreviousTimeStampHash")
    public JAXBElement<DigestAlgAndValueType> createPreviousTimeStampHash(DigestAlgAndValueType value) {
        return new JAXBElement<DigestAlgAndValueType>(_PreviousTimeStampHash_QNAME, DigestAlgAndValueType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bsi.bund.de/ecard/api/1.1", name = "ShowViewerResponse")
    public JAXBElement<ResponseType> createShowViewerResponse(ResponseType value) {
        return new JAXBElement<ResponseType>(_ShowViewerResponse_QNAME, ResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bsi.bund.de/ecard/api/1.1", name = "AddCertificateResponse")
    public JAXBElement<ResponseType> createAddCertificateResponse(ResponseType value) {
        return new JAXBElement<ResponseType>(_AddCertificateResponse_QNAME, ResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bsi.bund.de/ecard/api/1.1", name = "VerifyManifests")
    public JAXBElement<Object> createVerifyManifests(Object value) {
        return new JAXBElement<Object>(_VerifyManifests_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bsi.bund.de/ecard/api/1.1", name = "RegisterIFDResponse")
    public JAXBElement<ResponseType> createRegisterIFDResponse(ResponseType value) {
        return new JAXBElement<ResponseType>(_RegisterIFDResponse_QNAME, ResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bsi.bund.de/ecard/api/1.1", name = "DeleteTSLResponse")
    public JAXBElement<ResponseType> createDeleteTSLResponse(ResponseType value) {
        return new JAXBElement<ResponseType>(_DeleteTSLResponse_QNAME, ResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bsi.bund.de/ecard/api/1.1", name = "IncludeEContent")
    public JAXBElement<Object> createIncludeEContent(Object value) {
        return new JAXBElement<Object>(_IncludeEContent_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bsi.bund.de/ecard/api/1.1", name = "DeleteTrustedViewerResponse")
    public JAXBElement<ResponseType> createDeleteTrustedViewerResponse(ResponseType value) {
        return new JAXBElement<ResponseType>(_DeleteTrustedViewerResponse_QNAME, ResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bsi.bund.de/ecard/api/1.1", name = "SignatureForm")
    public JAXBElement<String> createSignatureForm(String value) {
        return new JAXBElement<String>(_SignatureForm_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UseVerificationTimeType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bsi.bund.de/ecard/api/1.1", name = "UseVerificationTime")
    public JAXBElement<UseVerificationTimeType> createUseVerificationTime(UseVerificationTimeType value) {
        return new JAXBElement<UseVerificationTimeType>(_UseVerificationTime_QNAME, UseVerificationTimeType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bsi.bund.de/ecard/api/1.1", name = "APIACLModifyResponse")
    public JAXBElement<ResponseType> createAPIACLModifyResponse(ResponseType value) {
        return new JAXBElement<ResponseType>(_APIACLModifyResponse_QNAME, ResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseBaseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bsi.bund.de/ecard/api/1.1", name = "VerifyResponse")
    public JAXBElement<ResponseBaseType> createVerifyResponse(ResponseBaseType value) {
        return new JAXBElement<ResponseBaseType>(_VerifyResponse_QNAME, ResponseBaseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EvidenceRecordType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bsi.bund.de/ecard/api/1.1", name = "EvidenceRecord")
    public JAXBElement<EvidenceRecordType> createEvidenceRecord(EvidenceRecordType value) {
        return new JAXBElement<EvidenceRecordType>(_EvidenceRecord_QNAME, EvidenceRecordType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bsi.bund.de/ecard/api/1.1", name = "FrameworkUpdate")
    public JAXBElement<RequestType> createFrameworkUpdate(RequestType value) {
        return new JAXBElement<RequestType>(_FrameworkUpdate_QNAME, RequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bsi.bund.de/ecard/api/1.1", name = "SetDefaultParametersResponse")
    public JAXBElement<ResponseType> createSetDefaultParametersResponse(ResponseType value) {
        return new JAXBElement<ResponseType>(_SetDefaultParametersResponse_QNAME, ResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bsi.bund.de/ecard/api/1.1", name = "TerminateFramework")
    public JAXBElement<RequestType> createTerminateFramework(RequestType value) {
        return new JAXBElement<RequestType>(_TerminateFramework_QNAME, RequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bsi.bund.de/ecard/api/1.1", name = "AddTrustedViewerResponse")
    public JAXBElement<ResponseType> createAddTrustedViewerResponse(ResponseType value) {
        return new JAXBElement<ResponseType>(_AddTrustedViewerResponse_QNAME, ResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bsi.bund.de/ecard/api/1.1", name = "DIDName")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    public JAXBElement<String> createDIDName(String value) {
        return new JAXBElement<String>(_DIDName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestBaseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bsi.bund.de/ecard/api/1.1", name = "SignRequest")
    public JAXBElement<RequestBaseType> createSignRequest(RequestBaseType value) {
        return new JAXBElement<RequestBaseType>(_SignRequest_QNAME, RequestBaseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bsi.bund.de/ecard/api/1.1", name = "SetDirectoryServicesResponse")
    public JAXBElement<ResponseType> createSetDirectoryServicesResponse(ResponseType value) {
        return new JAXBElement<ResponseType>(_SetDirectoryServicesResponse_QNAME, ResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bsi.bund.de/ecard/api/1.1", name = "TerminateFrameworkResponse")
    public JAXBElement<ResponseType> createTerminateFrameworkResponse(ResponseType value) {
        return new JAXBElement<ResponseType>(_TerminateFrameworkResponse_QNAME, ResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bsi.bund.de/ecard/api/1.1", name = "AddCardInfoFilesResponse")
    public JAXBElement<ResponseType> createAddCardInfoFilesResponse(ResponseType value) {
        return new JAXBElement<ResponseType>(_AddCardInfoFilesResponse_QNAME, ResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bsi.bund.de/ecard/api/1.1", name = "SetTSServicesResponse")
    public JAXBElement<ResponseType> createSetTSServicesResponse(ResponseType value) {
        return new JAXBElement<ResponseType>(_SetTSServicesResponse_QNAME, ResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bsi.bund.de/ecard/api/1.1", name = "AddTSLResponse")
    public JAXBElement<ResponseType> createAddTSLResponse(ResponseType value) {
        return new JAXBElement<ResponseType>(_AddTSLResponse_QNAME, ResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bsi.bund.de/ecard/api/1.1", name = "FrameworkUpdateResponse")
    public JAXBElement<ResponseType> createFrameworkUpdateResponse(ResponseType value) {
        return new JAXBElement<ResponseType>(_FrameworkUpdateResponse_QNAME, ResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bsi.bund.de/ecard/api/1.1", name = "InitializeFramework")
    public JAXBElement<RequestType> createInitializeFramework(RequestType value) {
        return new JAXBElement<RequestType>(_InitializeFramework_QNAME, RequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bsi.bund.de/ecard/api/1.1", name = "DeleteCertificateResponse")
    public JAXBElement<ResponseType> createDeleteCertificateResponse(ResponseType value) {
        return new JAXBElement<ResponseType>(_DeleteCertificateResponse_QNAME, ResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TrustedViewerInfoType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bsi.bund.de/ecard/api/1.1", name = "TrustedViewerInfo")
    public JAXBElement<TrustedViewerInfoType> createTrustedViewerInfo(TrustedViewerInfoType value) {
        return new JAXBElement<TrustedViewerInfoType>(_TrustedViewerInfo_QNAME, TrustedViewerInfoType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bsi.bund.de/ecard/api/1.1", name = "UnregisterIFDResponse")
    public JAXBElement<ResponseType> createUnregisterIFDResponse(ResponseType value) {
        return new JAXBElement<ResponseType>(_UnregisterIFDResponse_QNAME, ResponseType.class, null, value);
    }

}
