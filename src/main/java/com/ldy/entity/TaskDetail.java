package com.ldy.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author sunqing
 * @since 2022-07-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="TaskDetail对象", description="")
public class TaskDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("用户接单的记录")
    private Long userTaskId;

    @ApiModelProperty(value = "准驾车型")
    @TableField("Quasi_driving_type")
    private String quasiDrivingType;

    @ApiModelProperty(value = "注册号")
    @TableField("Registration_number")
    private String registrationNumber;

    @ApiModelProperty(value = "支付账号")
    @TableField("Payment_account")
    private String paymentAccount;

    @ApiModelProperty(value = "支付类型")
    @TableField("Payment_type")
    private String paymentType;

    @ApiModelProperty(value = "照片")
    private String photo;

    @ApiModelProperty(value = "账号信息")
    @TableField("Account_information")
    private String accountInformation;

    @ApiModelProperty(value = "预留手机")
    @TableField("The_reserved_phone")
    private String theReservedPhone;

    @ApiModelProperty(value = "与社会成员关系")
    @TableField("Relationships_with_members_of_society")
    private String relationshipsWithMembersOfSociety;

    @ApiModelProperty(value = "与家庭成员关系")
    @TableField("Relationships_with_family_members")
    private String relationshipsWithFamilyMembers;

    @ApiModelProperty(value = "有效期限")
    @TableField("Valid_term")
    private String validTerm;

    @ApiModelProperty(value = "银行账单")
    @TableField("The_bank_bills")
    private String theBankBills;

    @ApiModelProperty(value = "银行卡种类")
    @TableField("Type_of_bank_card")
    private String typeOfBankCard;

    @ApiModelProperty(value = "银行卡卡号")
    @TableField("Bank_card_Number")
    private String bankCardNumber;

    @ApiModelProperty(value = "虚拟身份类型")
    @TableField("Virtual_identity_type")
    private String virtualIdentityType;

    @ApiModelProperty(value = "详细程度")
    @TableField("How_detailed")
    private String howDetailed;

    @ApiModelProperty(value = "线索详细内容")
    @TableField("Clue_details")
    private String clueDetails;

    @ApiModelProperty(value = "线索涉及地")
    @TableField("The_clue_involves")
    private String theClueInvolves;

    @ApiModelProperty(value = "线索来源")
    @TableField("Clues_to_the_source")
    private String cluesToTheSource;

    @ApiModelProperty(value = "线索举报人")
    @TableField("Informant")
    private String informant;

    @ApiModelProperty(value = "线索标题")
    @TableField("Clues_to_the_title")
    private String cluesToTheTitle;

    @ApiModelProperty(value = "嫌疑人职业")
    @TableField("Occupation_of_suspect")
    private String occupationOfSuspect;

    @ApiModelProperty(value = "嫌疑人支付手机号码")
    @TableField("Suspects_pay_for_cell_phone_numbers")
    private String suspectsPayForCellPhoneNumbers;

    @ApiModelProperty(value = "嫌疑人姓名")
    @TableField("Name_of_suspect")
    private String nameOfSuspect;

    @ApiModelProperty(value = "嫌疑人性别")
    @TableField("Gender_of_suspect")
    private String genderOfSuspect;

    @ApiModelProperty(value = "嫌疑人手机号码")
    @TableField("Suspect_cell_phone_number")
    private String suspectCellPhoneNumber;

    @ApiModelProperty(value = "嫌疑人身份证号码")
    @TableField("Suspect_id_Number")
    private String suspectIdNumber;

    @ApiModelProperty(value = "嫌疑人户籍地")
    @TableField("Place_of_residence_of_the_suspect")
    private String placeOfResidenceOfTheSuspect;

    @ApiModelProperty(value = "通讯录")
    @TableField("The_address_book")
    private String theAddressBook;

    @ApiModelProperty(value = "所属专项")
    @TableField("Belongs_to_the_special")
    private String belongsToTheSpecial;

    @ApiModelProperty(value = "收件人电话")
    @TableField("Recipient_phone_number")
    private String recipientPhoneNumber;

    @ApiModelProperty(value = "收件人地址")
    @TableField("Addressee_address")
    private String addresseeAddress;

    @ApiModelProperty(value = "收件人")
    @TableField("The_recipient")
    private String theRecipient;

    @ApiModelProperty(value = "视频资料")
    @TableField("Video_data")
    private String videoData;

    @ApiModelProperty(value = "事件是否真实")
    @TableField("Whether_the_event_is_real_or_not")
    private String whetherTheEventIsRealOrNot;

    @ApiModelProperty(value = "涉及的毒品")
    @TableField("Drugs_involved")
    private String drugsInvolved;

    @ApiModelProperty(value = "涉毒种类")
    @TableField("Types_of_drug_related")
    private String typesOfDrugRelated;

    @ApiModelProperty(value = "涉毒嫌疑度")
    @TableField("Degree_of_suspicion_of_drug_involvement")
    private String degreeOfSuspicionOfDrugInvolvement;

    @ApiModelProperty(value = "涉毒规模")
    @TableField("Drug_related_scale")
    private String drugRelatedScale;

    @ApiModelProperty(value = "涉毒方式")
    @TableField("Drug_related_way")
    private String drugRelatedWay;

    @ApiModelProperty(value = "涉毒犯罪类型")
    @TableField("Types_of_drug_related_crimes")
    private String typesOfDrugRelatedCrimes;

    @ApiModelProperty(value = "涉毒地点")
    @TableField("Drug_related_site")
    private String drugRelatedSite;

    @ApiModelProperty(value = "涉案信息")
    @TableField("In_the_case_of_information")
    private String inTheCaseOfInformation;

    @ApiModelProperty(value = "社会成员职业")
    @TableField("Profession_of_social_member")
    private String professionOfSocialMember;

    @ApiModelProperty(value = "社会成员虚拟身份")
    @TableField("The_virtual_identity_of_a_social_member")
    private String theVirtualIdentityOfASocialMember;

    @ApiModelProperty(value = "社会成员姓名")
    @TableField("Name_of_social_member")
    private String nameOfSocialMember;

    @ApiModelProperty(value = "社会成员性别")
    @TableField("Gender_of_social_member")
    private String genderOfSocialMember;

    @ApiModelProperty(value = "社会成员手机号码")
    @TableField("Cell_phone_number_of_a_social_member")
    private String cellPhoneNumberOfASocialMember;

    @ApiModelProperty(value = "社会成员身份证号码")
    @TableField("Social_member_id_card_number")
    private String socialMemberIdCardNumber;

    @ApiModelProperty(value = "社会成员户籍地")
    @TableField("Place_of_residence_of_social_member")
    private String placeOfResidenceOfSocialMember;

    @ApiModelProperty(value = "签收时间")
    @TableField("To_sign_for_the_time")
    private String toSignForTheTime;

    @ApiModelProperty(value = "品牌")
    private String brand;

    @ApiModelProperty(value = "嫌疑度判断依据")
    @TableField("The_judgment_basis_of_suspicion")
    private String theJudgmentBasisOfSuspicion;

    @ApiModelProperty(value = "真实性判断依据")
    @TableField("Basis_of_authenticity_Judgment")
    private String basisOfAuthenticityJudgment;

    @ApiModelProperty(value = "年龄")
    private String age;

    @ApiModelProperty(value = "民族")
    private String national;

    @ApiModelProperty(value = "立案时间")
    @TableField("A_case_of_time")
    private String aCaseOfTime;

    @ApiModelProperty(value = "立案决定书")
    @TableField("Filing_decision")
    private String filingDecision;

    @ApiModelProperty(value = "来源可靠性")
    @TableField("Source_reliability")
    private String sourceReliability;

    @ApiModelProperty(value = "快递公司")
    @TableField("Courier_company")
    private String courierCompany;

    @ApiModelProperty(value = "快递单号")
    @TableField("Courier_number")
    private String courierNumber;

    @ApiModelProperty(value = "开户行名称")
    @TableField("Name_of_opening_bank")
    private String nameOfOpeningBank;

    @ApiModelProperty(value = "开户行地址")
    @TableField("Opening_bank_address")
    private String openingBankAddress;

    @ApiModelProperty(value = "居住地")
    @TableField("To_live")
    private String toLive;

    @ApiModelProperty(value = "经营范围")
    @TableField("Scope_of_business")
    private String scopeOfBusiness;

    @ApiModelProperty(value = "紧急程度")
    @TableField("The_degree_of_emergency")
    private String theDegreeOfEmergency;

    @ApiModelProperty(value = "家庭成员职业")
    @TableField("Family_member_occupation")
    private String familyMemberOccupation;

    @ApiModelProperty(value = "家庭成员虚拟身份")
    @TableField("Family_member_virtual_identity")
    private String familyMemberVirtualIdentity;

    @ApiModelProperty(value = "家庭成员姓名")
    @TableField("Name_of_family_member")
    private String nameOfFamilyMember;

    @ApiModelProperty(value = "家庭成员性别")
    @TableField("Gender_of_family_members")
    private String genderOfFamilyMembers;

    @ApiModelProperty(value = "家庭成员手机号码")
    @TableField("Family_member_mobile_phone_number")
    private String familyMemberMobilePhoneNumber;

    @ApiModelProperty(value = "家庭成员身份证号码")
    @TableField("Id_card_number_of_family_member")
    private String idCardNumberOfFamilyMember;

    @ApiModelProperty(value = "家庭成员户籍地")
    @TableField("Place_of_residence_of_a_family_member")
    private String placeOfResidenceOfAFamilyMember;

    @ApiModelProperty(value = "寄件人电话")
    @TableField("Return_telephone")
    private String returnTelephone;

    @ApiModelProperty(value = "寄件人地址")
    @TableField("Return_address")
    private String returnAddress;

    @ApiModelProperty(value = "寄件人")
    @TableField("The_sender")
    private String theSender;

    @ApiModelProperty(value = "机动车驾驶证号")
    @TableField("Motor_vehicle_driving_license_number")
    private String motorVehicleDrivingLicenseNumber;

    @ApiModelProperty(value = "获取时间")
    @TableField("To_get_the_time")
    private String toGetTheTime;

    @ApiModelProperty(value = "活动时间")
    @TableField("The_activity_time")
    private String theActivityTime;

    @ApiModelProperty(value = "活动轨迹描述")
    @TableField("Activity_trajectory_description")
    private String activityTrajectoryDescription;

    @ApiModelProperty(value = "话单")
    private String billed;

    @ApiModelProperty(value = "核查时间")
    @TableField("Check_the_time")
    private String checkTheTime;

    @ApiModelProperty(value = "固话号码")
    @TableField("Landline_number")
    private String landlineNumber;

    @ApiModelProperty(value = "房产所在地")
    @TableField("Location_of_property")
    private String locationOfProperty;

    @ApiModelProperty(value = "房产基本情况")
    @TableField("Real_Estate_Information")
    private String realEstateInformation;

    @ApiModelProperty(value = "房产编号")
    @TableField("Property_number")
    private String propertyNumber;

    @ApiModelProperty(value = "贩运方式")
    @TableField("Trafficking_way")
    private String traffickingWay;

    @ApiModelProperty(value = "发出时间")
    @TableField("A_time")
    private String aTime;

    @ApiModelProperty(value = "毒品流向")
    @TableField("Drug_flow")
    private String drugFlow;

    @ApiModelProperty(value = "单位名称")
    @TableField("Name_of_the_entity")
    private String nameOfTheEntity;

    @ApiModelProperty(value = "单位地址")
    @TableField("The_unit_address")
    private String theUnitAddress;

    @ApiModelProperty(value = "处置信息")
    @TableField("The_disposal_of_information")
    private String theDisposalOfInformation;

    @ApiModelProperty(value = "初次申领时间")
    @TableField("Initial_claim_time")
    private String initialClaimTime;

    @ApiModelProperty(value = "成立日期")
    @TableField("Set_up_the_date")
    private String setUpTheDate;

    @ApiModelProperty(value = "车牌号")
    @TableField("License_plate_number")
    private String licensePlateNumber;

    @ApiModelProperty(value = "车辆颜色")
    @TableField("Vehicle_color")
    private String vehicleColor;

    @ApiModelProperty(value = "车辆类型")
    @TableField("Vehicle_type")
    private String vehicleType;

    @ApiModelProperty(value = "查获信息")
    @TableField("Seized_information")
    private String seizedInformation;

    @ApiModelProperty(value = "案件名称")
    @TableField("Name_of_the_case")
    private String nameOfTheCase;

    @ApiModelProperty(value = "案件等级")
    @TableField("The_case_level")
    private String theCaseLevel;

    @ApiModelProperty(value = "案件编号")
    @TableField("The_case_number")
    private String theCaseNumber;



    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)

    private LocalDateTime updateTime;
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建人")
    private Long createUser;

    @ApiModelProperty(value = "修改人")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;

    @ApiModelProperty(value = "逻辑删除")
    private Integer deleted;

}
