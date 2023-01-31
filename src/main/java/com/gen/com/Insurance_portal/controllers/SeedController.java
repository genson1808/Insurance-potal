package com.gen.com.Insurance_portal.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gen.com.Insurance_portal.common.constanst.ClaimsCode;
import com.gen.com.Insurance_portal.common.enums.*;
import com.gen.com.Insurance_portal.entites.*;
import com.gen.com.Insurance_portal.entites.ProductCategory;
import com.gen.com.Insurance_portal.services.*;
import io.swagger.v3.oas.annotations.Hidden;
import org.json.JSONObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;


@Hidden
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/seed")
public class SeedController {

    private final IRoleService roleService;
    private final IProductCategoryService productCategoryService;
    private final IAuthoritiesGroupService authoritiesGroupService;
    private final IAuthoritiesService authoritiesService;
    private final PasswordEncoder passwordEncoder;
    private final IUserService userService;
    private final ISysAdminService sysAdminService;
    private final IProductService productService;
    private final ICarBrandService carBrandService;
    private final IClaimsConfigClientService claimsConfigClientService;
    private final ICarConfigClientService carConfigClientService;


    public SeedController(IRoleService roleService,
                          IProductCategoryService productCategoryService,
                          IAuthoritiesGroupService authoritiesGroupService,
                          IAuthoritiesService authoritiesService,
                          PasswordEncoder passwordEncoder,
                          IUserService userService,
                          ISysAdminService sysAdminService,
                          IProductService productService, ICarBrandService carBrandService,
                          IClaimsConfigClientService claimsConfigClientService,
                          ICarConfigClientService carConfigClientService) {

        this.roleService = roleService;
        this.productCategoryService = productCategoryService;
        this.authoritiesGroupService = authoritiesGroupService;
        this.authoritiesService = authoritiesService;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.sysAdminService = sysAdminService;
        this.productService = productService;
        this.carBrandService = carBrandService;
        this.claimsConfigClientService = claimsConfigClientService;
        this.carConfigClientService = carConfigClientService;
    }

    @GetMapping
    public String hello() {
        return "Welcome to here!";
    }



    @PreAuthorize("permitAll()")
    @GetMapping("/common")
    public String seedFAQ() {

        ProductCategory pc1 = new ProductCategory("Bảo hiểm sức khỏe", "Bảo hiểm sức khỏe");
        ProductCategory pc2 = new ProductCategory("Bảo hiểm du lịch", "Bảo hiểm du lịch");
        ProductCategory pc3 = new ProductCategory("Bảo hiểm tài sản", "Bảo hiểm tài sản");
        ProductCategory pc4 = new ProductCategory("Bảo hiểm xe", "Bảo hiểm xe");

        List<ProductCategory> productCategories = Arrays.asList(pc1, pc2, pc3, pc4);
        List<ProductCategory> productCategories1 = productCategoryService.saveAll(productCategories);


        Product product = new Product();
        product.setEffectiveDateRangeSelectionNumber(0);
        product.setAvatarImage("https://res.cloudinary.com/dmhmclzpm/image/upload/v1609757301/image_tceti6.png");
        product.setBannerImage("https://res.cloudinary.com/dmhmclzpm/image/upload/v1609757254/banner_vn7lnt.png");
        product.setCode("BHOT");
        product.setDetailedDescription("Chỉ với mức phí từ 1.300 đồng/ngày, quyền lợi lên đến 200 triệu đồng, bảo hiểm ô tô bắt buộc là một trong các giấy tờ xe luôn phải mang theo khi tham gia giao thông. Người điều khiển xe sẽ bị phạt từ 400.000 – 600.000 đồng khi không có bảo hiểm bắt buộc ô tô hoặc bảo hiểm đã hết hạn. Với ViettelPay, khách hàng có thêm lựa chọn để mở rộng quyền lợi bảo hiểm cho người ngồi trên xe lên đến 80 triêu đồng, với mức phí tăng thêm chỉ từ 40.000 đồng/năm. ");
        product.setEffectiveDateType(EffectiveDateType.T);
        product.setFeeType(FeeType.FIXED);
        product.setGenderApply(GenderApply.ALL);
        product.setShortDescription("https://res.cloudinary.com/dmhmclzpm/image/upload/v1609757613/insurance_lljsqd.png");
        product.setName("Bảo hiểm xe ô tô");
        product.setPriceObj(200000000.);
        product.setProductStatus(ProductStatus.APPROVED);
        product.setProductCategory(productCategories1.get(3));
        product.setRepaintFee(80);
        product.setNumberRepaint(2);
        product.setBringingFee(100);
        product.setNumberBringing(2);
        product.setComponentFee(50);
        product.setNumberComponent(2);
        product.setRearViewMirror(70);
        product.setNumberRearViewMirror(3);
        product.setScratchedFee(75);
        product.setNumberScratched(2);
        productService.save(product);

        return "seed succeed!";
    }



    @PreAuthorize("permitAll()")
    @RequestMapping("/authorities")
    public String seedAuthorities() throws JsonProcessingException {

        Role admin = roleService.save(new Role("admin role", "ADMIN"));
        admin.setActive(true);
        roleService.save(new Role("customer role", "CUSTOMER"));
        roleService.save(new Role("partner role", "PARTNER"));


        JSONObject jsonObject1 = new JSONObject("{\"models\":[{\"title\":\"Lux SA2.0 2019\",\"code\":\"VICSA2019\",\"price\":\"100000\"},{\"title\":\"Lux A2.0 2019\",\"code\":\"VICA2019\",\"price\":\"50000\"},{\"title\":\"Fadil 2019\",\"code\":\"VICF\",\"price\":\"20000\"}]}");
        String models1 = jsonObject1.get("models").toString();

        JSONObject jsonObject2 = new JSONObject("{\"models\":[{\"title\":\"Elantra 2019\",\"code\":\"HYNELT\",\"price\":\"30000\"}]}");
        String models2 = jsonObject2.get("models").toString();

        JSONObject jsonObject3 = new JSONObject("{\"models\":[{\"title\":\"MERCEDES - C300 2020\",\"code\":\"C300\",\"price\":\"50000\"},{\"title\":\"MERCEDES - C200 2020\",\"code\":\"C200\",\"price\":\"40000\"}]}");
        String models3 = jsonObject3.get("models").toString();

        JSONObject jsonObject4 = new JSONObject("{\"models\":[{\"title\":\"Audi Coupe\",\"code\":\"AUC\",\"price\":\"70000\"},{\"title\":\"Audi Hatchback\",\"code\":\"AUH\",\"price\":\"90000\"},{\"title\":\"Audi SUV\",\"code\":\"AUSUV\",\"price\":\"120000\"},{\"title\":\"Audi Sedan\",\"code\":\"AUS\",\"price\":\"50000\"}]}");
        String models4 = jsonObject4.get("models").toString();

        JSONObject jsonObject5 = new JSONObject("{\"models\": [{\"title\":\"BMW 4 Series\",\"code\":\"BMW4\",\"price\":\"80000\"},{\"title\":\"BMW 5 Series\",\"code\":\"BMW5\",\"price\":\"85000\"},{\"title\":\"BMW 7 Series\",\"code\":\"BMW7\",\"price\":\"110000\"},{\"title\":\"BMW X\",\"code\":\"BMWX\",\"price\":\"130000\"}]}");
        String models5 = jsonObject5.get("models").toString();

        JSONObject jsonObject6 = new JSONObject( "{\"models\": [{\"title\":\"TOYOTA CAMRY\",\"code\":\"TYTC\",\"price\":\"40000\"},{\"title\":\"TOYOTA INNOVA\",\"code\":\"TYTI\",\"price\":\"23000\"},{\"title\":\"TOYOTA VIOS\",\"code\":\"TYTV\",\"price\":\"20000\"},{\"title\":\"TOYOTA FORTUNER\",\"code\":\"TYTF\",\"price\":\"38000\"}]}");
        String models6 = jsonObject6.get("models").toString();

        JSONObject jsonObject7 = new JSONObject("{\"models\":[{\"title\":\"Honda Brio\",\"code\":\"HDB\",\"price\":\"15000\"},{\"title\":\"Honda Civic\",\"code\":\"HDCV\",\"price\":\"50000\"},{\"title\":\"Honda CRV\",\"code\":\"HDC\",\"price\":\"55000\"}]}");
        String models7 = jsonObject7.get("models").toString();

        JSONObject jsonObject8 = new JSONObject("{\"models\": [{\"title\":\"Mazda 3\",\"code\":\"MD3\",\"price\":\"45000\"},{\"title\":\"Mazda CX-5\",\"code\":\"MDCX5\",\"price\":\"50000\"},{\"title\":\"Mazda 6\",\"code\":\"MD6\",\"price\":\"49000\"}]}");
        String models8 = jsonObject8.get("models").toString();

        JSONObject jsonObject9 = new JSONObject("{\"models\":[{\"title\":\"Ford Everes\",\"code\":\"FDE\",\"price\":\"60000\"},{\"title\":\"Ford Ranger\",\"code\":\"\",\"price\":\"30000\"}]}");
        String models9 = jsonObject9.get("models").toString();


        JSONObject jsonObject10 = new JSONObject("{\"models\":[{\"title\":\"Kia Morning\",\"code\":\"KIAM\",\"price\":\"20000\"},{\"title\":\"Kia Soluto\",\"code\":\"KIAS\",\"price\":\"18000\"},{\"title\":\"Kia Optima\",\"code\":\"KIAO\",\"price\":\"40000\"},{\"title\":\"Kia Telluride\",\"code\":\"KIAT\",\"price\":\"50000\"}]}");
        String models10 = jsonObject10.get("models").toString();

        carBrandService.save(new CarBrand("Vinfast", "VIC", models1));
        carBrandService.save(new CarBrand("Huyndai", "HYN", models2));
        carBrandService.save(new CarBrand("MERCEDES", "MEC", models3));
        carBrandService.save(new CarBrand("Audi", "AU", models4));
        carBrandService.save(new CarBrand("Bayerische Motoren Werke", "BMW", models5));
        carBrandService.save(new CarBrand("Toyota", "TYT", models6));
        carBrandService.save(new CarBrand("HONDA", "HD", models7));
        carBrandService.save(new CarBrand("Mazda", "MD", models8));
        carBrandService.save(new CarBrand("Ford", "FD", models9));
        carBrandService.save(new CarBrand("KIA", "KIA", models10));


        claimsConfigClientService.save(new ClaimsConfigClient("https://firebasestorage.googleapis.com/v0/b/insurance-admin-580a8.appspot.com/o/Admin%2FClaim%20Config%20Banner?alt=media&token=4a237b20-c3d6-4626-bd72-d7feb9207093",
                                                                "<h3><strong>I. Coverage</strong></h3><p class=\\\"ql-indent-1\\\">Insurance to indemnify the owner of the vehicle for physical damage caused by:</p><p class=\\\"ql-indent-2\\\">1 Unexpected accidents, beyond the control of the owner, driving in the following cases: crash</p><p class=\\\"ql-indent-2\\\">crash, overturn, overturn, fall; sank; fire, fire, explosion; being dropped or bumped by other objects.</p><p class=\\\"ql-indent-2\\\">2 Force majeure events caused by nature: Storms, floods, landslides, lightning, earthquakes, hail,</p><p class=\\\"ql-indent-2\\\">tsunami.</p><p class=\\\"ql-indent-2\\\">3 Stolen, robbed the entire vehicle.</p><h3><strong>II. Necessary costs under the scope of insurance</strong></h3><p class=\\\"ql-indent-1\\\">insurrance pays necessary and reasonable expenses arising from an accident under the scope of insurance</p><p class=\\\"ql-indent-1\\\">(not exceeding 10% of the sum insured) in order to prevent and limit additional losses,</p><p class=\\\"ql-indent-1\\\">Protect and bring damage to the nearest place to repair, assess damage.</p><h3><strong>III. Sum insured</strong></h3><p class=\\\"ql-indent-1\\\">The sum insured is specified on the Policy / Certificate of Insurance / Policy</p><p class=\\\"ql-indent-1\\\">Insurance / Supplement. Vehicle owner can negotiate with insurrance to insure with the Item Insurance Amount</p><p class=\\\"ql-indent-1\\\">vehicle quality is equal to or lower than the market value of the vehicle.</p><p class=\\\"ql-indent-1\\\">Car owners should participate in insurance equal to the market value to ensure full insurance benefits</p><p class=\\\"ql-indent-1\\\">when unfortunately there is a loss.</p><h3><strong>IV. Insurance benefits settlement</strong></h3><p class=\\\"ql-indent-1\\\">insurrance is responsible for paying the actual cost of repairing, replacing (if unable to repair</p><p class=\\\"ql-indent-1\\\">repair) of the part, or pay the owner to compensate the loss on a determined basis</p><p class=\\\"ql-indent-1\\\">The cost of repairing or repairing the loss may be payable.</p><p class=\\\"ql-indent-1\\\">The actual repair cost will be based on the quotation of the associated garage / repair shop system</p><p class=\\\"ql-indent-1\\\">with insurrance (except for the case participating in supplementary clause 03-BVVC).</p><p class=\\\"ql-indent-1\\\">Damaged parts or car bodies have been approved for compensation by insurrance (or replaced).</p><p class=\\\"ql-indent-1\\\">owned by insurrance; Or insurrance recovers the residual value of the car body according to the ratio of the number</p><p class=\\\"ql-indent-1\\\">Compensation money ofinsurrance and the market price of the car (in case of under-value insurance, insurance</p><p class=\\\"ql-indent-1\\\">insurance or third party indemnity).</p><p class=\\\"ql-indent-1\\\">1 Compensation for part loss</p><ul><li class=\\\"ql-indent-1\\\">In case the car is insured with the insured amount lower than the market price, the compensation amount</li></ul><p class=\\\"ql-indent-2\\\">Normally, insurrance is calculated according to the ratio between the sum insured and the market price of the vehicle.</p><ul><li class=\\\"ql-indent-1\\\">In the process of repairing or overcoming losses, if a new part must be replaced, Bao</li></ul><p class=\\\"ql-indent-2\\\">Viet will determine the cost of replacing materials and spare parts under insurance liability by expenditure</p><p class=\\\"ql-indent-2\\\">Actual costs of replacing such supplies and spare parts and depreciation are applied according to the following rates:</p><p class=\\\"ql-indent-2\\\">CAR INSURANCE:</p><p class=\\\"ql-indent-3\\\">- Vehicle usage time up to 03 years, 0% reduction of replacement part value.</p><p class=\\\"ql-indent-3\\\">- Vehicle use time of more than 03 years to less than 06 years is reduced by 15% of the new part value.</p><p class=\\\"ql-indent-3\\\">- Vehicle use time from 06 years to less than 10 years is reduced by 25% of the replacement part value.</p><p class=\\\"ql-indent-3\\\">- Vehicle use time from 10 years to less than 15 years is reduced 35% of the replacement part value.</p><p class=\\\"ql-indent-3\\\">- If the vehicle is used for 15 years or more, 50% reduction of the replacement part value.</p><ul><li class=\\\"ql-indent-1\\\">insurrance compensates for the full cost of repainting the vehicle if more than 50% of the painted area of \u200B\u200Bthe vehicle is damaged</li></ul><p class=\\\"ql-indent-2\\\">failure caused by accident according to the calculation at item a and b, point 11.1 Article 11 of this Code.</p><p class=\\\"ql-indent-1\\\">2 Compensation for total loss</p><p class=\\\"ql-indent-1\\\">A vehicle is considered total loss in the following cases:</p><ul><li class=\\\"ql-indent-1\\\">The vehicle repair estimate was approved by insurrance with the above estimated repair cost</li></ul><p class=\\\"ql-indent-2\\\">75% of the market price of cars.</p><ul><li class=\\\"ql-indent-1\\\">Vehicle is stolen, robbed (with investigation conclusions or decision to suspend the investigation</li></ul><p class=\\\"ql-indent-2\\\">investigation agency).</p><p class=\\\"ql-indent-1\\\">The full compensation amount is equal to the market value of the vehicle before the accident (loss) and</p><p class=\\\"ql-indent-1\\\">not to exceed the sum insured stated in the Contract / Certificate / Policy.</p><p class=\\\"ql-indent-1\\\">3 Deductible is the amount of money that the owner of the vehicle must bear in each loss. Discount rate</p><p class=\\\"ql-indent-1\\\">The deduction is specified in the Contract / Certificate / Policy concluded with / issued to the Vehicle Owner.</p><p class=\\\"ql-indent-1\\\">If a deduction is not specified, the deduction level is VND 500,000 / loss case.</p>",
                                                               "What Would You Like to Claim For?"));


        carConfigClientService.save(new CarConfigClient("https://firebasestorage.googleapis.com/v0/b/insurance-admin-580a8.appspot.com/o/Admin%2FCar%20Config%20Banner?alt=media&token=70750d4f-2326-4a94-be4d-f0e2c881e0c1",
                "<h1>What is auto insurance?</h1><p><br></p><p>This type of insurance is very necessary for cars to join traffic because roads and traffic infrastructure in our country are still poor so participating in insurance is the best way to limit damage and the cost when the risk occurs.</p><p><br></p><p>Many car owners often think that using a car for a period of time to repair the body will buy this insurance to receive a part of the cost of support from auto insurance companies. But that is a very wrong view because before you sign the insurance policy with you, the insurance companies will do a status assessment of the vehicle.</p>",
                "body content?"));

        User user = new User();
        user.setGivenName("admin");
        user.setSurname("admin");
        user.setRole(admin);
        user.setEmail("admin@gmail.com");
        user.setUsername("admin");
        user.setPassword(passwordEncoder.encode("Admin@12345"));
        user.setGender(Gender.NONE);
        user.setIsActive(true);
        user.setFromLegacySystem(true);
        User newUser = userService.save(user);

        SysAdmin sysAdmin = new SysAdmin();
        sysAdmin.setUser(user);
        sysAdmin.setIsActive(true);
        sysAdmin.setType(SysAdminType.ADMIN);
        sysAdminService.save(sysAdmin);

        AuthoritiesGroup productGroup = authoritiesGroupService.save(new AuthoritiesGroup("Sản phẩm"));

        // Sản phẩm
        Authorities a1 = new Authorities(ClaimsCode.ProductCreate, "Khai báo sản phẩm", productGroup);
        Authorities a2 = new Authorities(ClaimsCode.ProductEdit, "sửa thông tin sản phẩm", productGroup);
        Authorities a3 = new Authorities(ClaimsCode.ProductStatus, "Active / Deactive sản phẩm", productGroup);
        Authorities a4 = new Authorities(ClaimsCode.ProductApprovement, "Duyệt thông tin sản pphẩm", productGroup);
        Authorities a5 = new Authorities(ClaimsCode.ProductList, "Xem danh sách sản phẩm", productGroup);
        Authorities a6 = new Authorities(ClaimsCode.ProductDetail, "Xem chi tiết sản phẩm", productGroup);
        Authorities a69 = new Authorities(ClaimsCode.ProductDelete, "Xoá sản phẩm", productGroup);

        List<Authorities> productAuthorities = Arrays.asList(a1, a2, a3, a4, a5, a6, a69);
        admin.getAuthorities().addAll(productAuthorities);
        authoritiesService.saveAll(productAuthorities);


        //---------------------------------------------------------------------------------------------->



        AuthoritiesGroup attributeGroup = authoritiesGroupService.save(new AuthoritiesGroup("Thuộc tính"));

        // Thuộc tính
        Authorities a7 = new Authorities(ClaimsCode.AttributeCreate, "thêm thuộc tính", attributeGroup);
        Authorities a8 = new Authorities(ClaimsCode.AttributeEdit, "sửa thuộc tính", attributeGroup);
        Authorities a9 = new Authorities(ClaimsCode.AttributeStatus, "Active / Deactive thuộc tính", attributeGroup);
        Authorities a10 = new Authorities(ClaimsCode.AttributeList, "xem danh sách thuộc tính", attributeGroup);
        Authorities a11 = new Authorities(ClaimsCode.AttributeDetail, "xem chi tiết thuộc tính", attributeGroup);

        List<Authorities> attributeAuthorities = Arrays.asList(a7, a8, a9, a10, a11);
        admin.getAuthorities().addAll(attributeAuthorities);
        authoritiesService.saveAll(attributeAuthorities);


        //---------------------------------------------------------------------------------------------->


        AuthoritiesGroup customerGroup = authoritiesGroupService.save(new AuthoritiesGroup("Khách hàng"));

        // Khách hàng
        Authorities a12 = new Authorities(ClaimsCode.CustomerList, "xem danh sách khách hàng", customerGroup);
        Authorities a67 = new Authorities(ClaimsCode.CustomerDetail, "xem chi tiết khách hàng", customerGroup);
        List<Authorities> customerAuthorities = Arrays.asList(a12, a67);
        admin.getAuthorities().addAll(customerAuthorities);
        authoritiesService.saveAll(customerAuthorities);


        //---------------------------------------------------------------------------------------------->


        // Đối tác:
        AuthoritiesGroup partnersGroup = authoritiesGroupService.save(new AuthoritiesGroup("Đối tác"));

        // --> đối tác

        AuthoritiesGroup partnerGroup = authoritiesGroupService.save(new AuthoritiesGroup("đối tác", partnersGroup));

        Authorities a13 = new Authorities(ClaimsCode.PartnerCreate, "khai báo đối tác", partnerGroup);
        Authorities a14 = new Authorities(ClaimsCode.PartnerEdit, "sửa thông tin đối tác", partnerGroup);
        Authorities a15 = new Authorities(ClaimsCode.PartnerStatus, "Active / Deactive đối tác", partnerGroup);
        Authorities a16 = new Authorities(ClaimsCode.PartnerApprovement, "duyệt thông tin đối tác", partnerGroup);
        Authorities a17 = new Authorities(ClaimsCode.PartnerList, "xem danh sách đối tác", partnerGroup);
        Authorities a68 = new Authorities(ClaimsCode.PartnerDetail, "xem chi tiết đối tác", partnerGroup);

        List<Authorities> partnerAuthorities = Arrays.asList(a13, a14, a15, a16, a17, a68);
        admin.getAuthorities().addAll(partnerAuthorities);
        authoritiesService.saveAll(partnerAuthorities);


        // --> số hợp đồng

        AuthoritiesGroup contractNumberGroup = authoritiesGroupService.save(new AuthoritiesGroup("số hợp đồng", partnersGroup));
        Authorities a18 = new Authorities(ClaimsCode.ContractNumberCreate, "tạo số hợp đồng", contractNumberGroup);
        Authorities a19 = new Authorities(ClaimsCode.ContractNumberEdit, "sửa thông tin số hợp đồng", contractNumberGroup);
        Authorities a20 = new Authorities(ClaimsCode.ContractNumberList, "xem danh sách số hợp đồng", contractNumberGroup);

        List<Authorities> contractNumberAuthorities = Arrays.asList(a18, a19, a20);
        admin.getAuthorities().addAll(contractNumberAuthorities);
        authoritiesService.saveAll(contractNumberAuthorities);


        //---------------------------------------------------------------------------------------------->


        AuthoritiesGroup contactGroup = authoritiesGroupService.save(new AuthoritiesGroup("Hợp đồng"));

        // Hợp đồng
        Authorities a21 = new Authorities(ClaimsCode.ContractRequest, "yêu cầu điều chỉnh bồi thường",contactGroup);
        Authorities a22 = new Authorities(ClaimsCode.ContractCompensation, "bồi thường", contactGroup);
        Authorities a23 = new Authorities(ClaimsCode.ContractRefuse, "từ chối bồi thường", contactGroup);
        Authorities a24 = new Authorities(ClaimsCode.ContractList, "xem danh sách hợp đồng", contactGroup);

        List<Authorities> contactAuthorities = Arrays.asList(a21, a22, a23, a24);
        admin.getAuthorities().addAll(contactAuthorities);
        authoritiesService.saveAll(contactAuthorities);


        //---------------------------------------------------------------------------------------------->


        AuthoritiesGroup roleGroup = authoritiesGroupService.save(new AuthoritiesGroup("Phân quyền"));

        // Phân quyền
        Authorities a25 = new Authorities(ClaimsCode.RoleCreate, "thiết lập nhóm quyền", roleGroup);
        Authorities a26 = new Authorities(ClaimsCode.RoleEdit, "chỉnh sửa nhóm quyền", roleGroup);
        Authorities a27 = new Authorities(ClaimsCode.RoleList, "xem danh sách nhóm quyền", roleGroup);
        Authorities a28 = new Authorities(ClaimsCode.RoleStatus, "Active / Deactive nhóm quyền", roleGroup);
        Authorities a29 = new Authorities(ClaimsCode.RoleDetail, "xem chi tiết nhóm quyền", roleGroup);

        List<Authorities> roleAuthorities = Arrays.asList(a25, a26, a27, a28, a29);
        admin.getAuthorities().addAll(roleAuthorities);
        authoritiesService.saveAll(roleAuthorities);


        //---------------------------------------------------------------------------------------------->


        AuthoritiesGroup articleGroup = authoritiesGroupService.save(new AuthoritiesGroup("Tin bài"));

        // Tin bài
        Authorities a30 = new Authorities(ClaimsCode.ArticleCreate, "thêm tin bài", articleGroup);
        Authorities a31 = new Authorities(ClaimsCode.ArticleEdit, "sửa tin bài", articleGroup);
        Authorities a32 = new Authorities(ClaimsCode.ArticleList, "xem danh sách tin bài", articleGroup);
        Authorities a33 = new Authorities(ClaimsCode.ArticleStatus, "Active / Deactive tin bài", articleGroup);
        Authorities a34 = new Authorities(ClaimsCode.ArticleDetail, "xem chi tiết tin bài", articleGroup);

        List<Authorities> articleAuthorities = Arrays.asList(a30, a31, a32, a33, a34);
        admin.getAuthorities().addAll(articleAuthorities);
        authoritiesService.saveAll(articleAuthorities);


        //---------------------------------------------------------------------------------------------->


        AuthoritiesGroup faqGroup = authoritiesGroupService.save(new AuthoritiesGroup("Câu hỏi thường gặp"));

        // Câu hỏi thường gặp
        Authorities a35 = new Authorities(ClaimsCode.FAQCreate, "thêm câu hỏi thường gặp", faqGroup);
        Authorities a36 = new Authorities(ClaimsCode.FAQEdit, "sửa câu hỏi thường gặp", faqGroup);
        Authorities a37 = new Authorities(ClaimsCode.FAQList, "xem danh sách câu hỏi thường gặp", faqGroup);
        Authorities a38 = new Authorities(ClaimsCode.FAQStatus, "Active / Deactive câu hỏi thường gặp", faqGroup);
        Authorities a39 = new Authorities(ClaimsCode.FAQDetail, "xem chi tiết câu hỏi thường gặp", faqGroup);

        List<Authorities> faqAuthorities = Arrays.asList(a35, a36, a37, a38, a39);
        admin.getAuthorities().addAll(faqAuthorities);
        authoritiesService.saveAll(faqAuthorities);
        //---------------------------------------------------------------------------------------------->


        AuthoritiesGroup historyTransactionGroup = authoritiesGroupService.save(new AuthoritiesGroup("Lịch sử giao dịch"));

        // Lịch sử giao dịch
        Authorities a40 = new Authorities(ClaimsCode.HistoryTransactionList, "xem lịch sử giao dịch", historyTransactionGroup);
        Authorities a41 = new Authorities(ClaimsCode.HistoryTransactionExport, "xuất file lịch sử giao dịch", historyTransactionGroup);

        List<Authorities> historyTransactionAuthorities = Arrays.asList(a40, a41);
        admin.getAuthorities().addAll(historyTransactionAuthorities);
        authoritiesService.saveAll(historyTransactionAuthorities);


        //---------------------------------------------------------------------------------------------->


        AuthoritiesGroup articleCategoryGroup = authoritiesGroupService.save(new AuthoritiesGroup("Quản lý chuyên mục"));

        // Quản lý chuyên mục
        Authorities a42 = new Authorities(ClaimsCode.ArticleCategoryCreate, "thêm chuyển mục", articleCategoryGroup);
        Authorities a43 = new Authorities(ClaimsCode.ArticleCategoryEdit, "sửa chuyên mục", articleCategoryGroup);
        Authorities a44 = new Authorities(ClaimsCode.ArticleCategoryList, "xem danh sách chuyên mục", articleCategoryGroup);
        Authorities a45 = new Authorities(ClaimsCode.ArticleCategoryDetail, "xem chi tiết chuyên mục", articleCategoryGroup);
        Authorities a46 = new Authorities(ClaimsCode.ArticleCategoryStatus, "Active / Deactive chuyên mục", articleCategoryGroup);

        List<Authorities> articleCategoryAuthorities = Arrays.asList(a42, a43, a44, a45, a46);
        admin.getAuthorities().addAll(articleCategoryAuthorities);
        authoritiesService.saveAll(articleCategoryAuthorities);

        //---------------------------------------------------------------------------------------------->



        AuthoritiesGroup productOrderGroup = authoritiesGroupService.save(new AuthoritiesGroup("Sản phẩm nổi bật"));

        // Sản phẩm nổi bật
        Authorities a48 = new Authorities(ClaimsCode.ProductOrderConfig, "quản lý sản phẩm nổi bật", productOrderGroup);
        admin.getAuthorities().add(a48);
        authoritiesService.save(a48);



        //---------------------------------------------------------------------------------------------->

        AuthoritiesGroup rateChargeGroup = authoritiesGroupService.save(new AuthoritiesGroup("Quản lý tỉ lệ phí"));

        // Quản lý tỉ lệ phí
        Authorities a49 = new Authorities(ClaimsCode.RateChargeCreate, "thêm tỉ lệ phí", rateChargeGroup);
        Authorities a50 = new Authorities(ClaimsCode.RateChargeEdit, "sửa tỉ lệ phí", rateChargeGroup);
        Authorities a51 = new Authorities(ClaimsCode.RateChargeList, "xem danh sách tỉ lệ phí", rateChargeGroup);
        Authorities a52 = new Authorities(ClaimsCode.RateChargeDetail, "xem chi tiết tỉ lệ phí", rateChargeGroup);
        Authorities a53 = new Authorities(ClaimsCode.RateChargeStatus, "Active / Deactive tỉ lệ phí", rateChargeGroup);

        List<Authorities> rateChargeAuthorities = Arrays.asList(a49, a50, a51, a52, a53);
        admin.getAuthorities().addAll(rateChargeAuthorities);
        authoritiesService.saveAll(rateChargeAuthorities);

        //---------------------------------------------------------------------------------------------->


        AuthoritiesGroup orderPendingGroup = authoritiesGroupService.save(new AuthoritiesGroup("Đơn hàng chờ thanh toán"));

        // Đơn hàng chờ thanh toán
        Authorities a55 = new Authorities(ClaimsCode.OrderPendingList, "xem danh sách đơn hàng chờ thanh toán", orderPendingGroup);
        Authorities a56 = new Authorities(ClaimsCode.OrderPendingDetail, "xem chi tiết đơn hàng chờ thanh toán", orderPendingGroup);
        Authorities a57 = new Authorities(ClaimsCode.OrderPendingCreatePolicy, "tạo hợp đồng cho đơn hàng chờ thanh toán", orderPendingGroup);

        List<Authorities> orderPendingAuthorities = Arrays.asList(a55, a56, a57);
        admin.getAuthorities().addAll(orderPendingAuthorities);
        authoritiesService.saveAll(orderPendingAuthorities);


        //---------------------------------------------------------------------------------------------->



        AuthoritiesGroup targetGroupGroup = authoritiesGroupService.save(new AuthoritiesGroup("Nhóm đối tượng"));

        // Nhóm đối tượng
        Authorities a58 = new Authorities(ClaimsCode.TargetGroupCreate, "tạo mới nhóm đối tượng", targetGroupGroup);
        Authorities a59 = new Authorities(ClaimsCode.TargetGroupEdit, "sửa nhóm đối tượng", targetGroupGroup);
        Authorities a60 = new Authorities(ClaimsCode.TargetGroupList, "xem danh sách nhóm đối tượng", targetGroupGroup);
        Authorities a61 = new Authorities(ClaimsCode.TargetGroupDetail, "xem chi tiết nhóm đối tượng", targetGroupGroup);
        Authorities a62 = new Authorities(ClaimsCode.TargetGroupStatus, "Active / Deactive nhóm đối tượng", targetGroupGroup);

        List<Authorities> targetGroupAuthorities = Arrays.asList(a58, a59, a60, a61, a62);
        admin.getAuthorities().addAll(targetGroupAuthorities);
        authoritiesService.saveAll(targetGroupAuthorities);


        //---------------------------------------------------------------------------------------------->


        AuthoritiesGroup promoGroup = authoritiesGroupService.save(new AuthoritiesGroup("Mã khuyến mãi"));

        // Mã khuyến mãi
        Authorities a63 = new Authorities(ClaimsCode.PromoCreate, "tạo mã khuyến mãi", promoGroup);
        Authorities a64 = new Authorities(ClaimsCode.PromoEdit, "sửa mã khuyến mãi", promoGroup);
        Authorities a65 = new Authorities(ClaimsCode.PromoIndex, "xem danh sách mã khuyến mãi", promoGroup);

        List<Authorities> promoAuthorities = Arrays.asList(a63, a64, a65);
        admin.getAuthorities().addAll(promoAuthorities);
        authoritiesService.saveAll(promoAuthorities);


        AuthoritiesGroup claimsGroup = authoritiesGroupService.save(new AuthoritiesGroup("Claims"));

        // Claims
        Authorities a100 = new Authorities(ClaimsCode.ClaimsList, "danh sách bồi thường", claimsGroup);
        Authorities a101 = new Authorities(ClaimsCode.ClaimsStatus, "claims status", claimsGroup);
        Authorities a102 = new Authorities(ClaimsCode.RequiredClaims, "yếu cầu bồi thường", promoGroup);

        List<Authorities> claimsAuthorities = Arrays.asList(a100, a101, a102);
        admin.getAuthorities().addAll(claimsAuthorities);
        authoritiesService.saveAll(claimsAuthorities);

        AuthoritiesGroup allGroup = authoritiesGroupService.save(new AuthoritiesGroup("All"));
        Authorities a103 = new Authorities("All_Authorities", "All Authorities", allGroup);
        admin.getAuthorities().add(a103);
        authoritiesService.save(a103);





        //---------------------------------------------------------------------------------------------->


        AuthoritiesGroup siteConfigGroup = authoritiesGroupService.save(new AuthoritiesGroup("Cấu hình cho website"));

        // Cấu hình cho website
        Authorities a66 = new Authorities(ClaimsCode.SiteConfigIndex, "cấu hình cho website", siteConfigGroup);
        admin.getAuthorities().add(a66);
        authoritiesService.save(a66);

        roleService.update(admin);


        return "seed succeed!";
    }

}
