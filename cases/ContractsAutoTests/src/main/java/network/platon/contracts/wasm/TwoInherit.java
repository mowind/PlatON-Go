package network.platon.contracts.wasm;

import com.platon.rlp.datatypes.Uint8;
import java.math.BigInteger;
import java.util.Arrays;
import org.web3j.abi.WasmFunctionEncoder;
import org.web3j.abi.datatypes.WasmFunction;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.WasmContract;
import org.web3j.tx.gas.GasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://github.com/PlatONnetwork/client-sdk-java/releases">platon-web3j command line tools</a>,
 * or the org.web3j.codegen.WasmFunctionWrapperGenerator in the 
 * <a href="https://github.com/PlatONnetwork/client-sdk-java/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with platon-web3j version 0.13.0.6.
 */
public class TwoInherit extends WasmContract {
    private static String BINARY_0 = "0x0061736d0100000001480d60027f7f0060017f0060017f017f60027f7f017f60037f7f7f0060037f7f7f017f60000060047f7f7f7f017f60047f7f7f7f0060027f7e006000017f60027f7e017f60017f017e02a9010703656e760c706c61746f6e5f70616e6963000603656e7617706c61746f6e5f6765745f696e7075745f6c656e677468000a03656e7610706c61746f6e5f6765745f696e707574000103656e7617706c61746f6e5f6765745f73746174655f6c656e677468000303656e7610706c61746f6e5f6765745f7374617465000703656e7610706c61746f6e5f7365745f7374617465000803656e760d706c61746f6e5f72657475726e00000352510601010101000303070001040304020002060205040102000c0201030200020901000b000607020900030505040001030003000503000301000205000000000800020005070105040006040602020800000405017001050505030100020608017f0141a08b040b073904066d656d6f72790200115f5f7761736d5f63616c6c5f63746f727300070f5f5f66756e63735f6f6e5f65786974002b06696e766f6b650018090a010041010b04091214090af168510f0041e00810084101100a105010520b170020004200370200200041086a41003602002000100b0b0300010b970101027f41ec08410136020041f008280200220145044041f00841f80836020041f80821010b024041f4082802002202412046044041840210192201450d0120014184021022220141f00828020036020041f008200136020041f4084100360200410021020b41f408200241016a360200200120024102746a22014184016a4100360200200141046a20003602000b41ec0841003602000b2201017f03402001410c470440200020016a4100360200200141046a21010c010b0b0b8a0101047f230041206b2202240002402000411c6a2802002203200041206a220428020047044020032001100d1a2000200028021c413c6a36021c0c010b200241086a200041186a2205200320002802186b413c6d220041016a100e20002004100f22002802082001100d1a20002000280208413c6a360208200520001010200010110b200241206a24000b3f002000200110131a2000410c6a2001410c6a10131a200041186a200141186a10131a200041246a200141246a10131a200041306a200141306a10131a20000b2f01017f2001200028020820002802006b413c6d2200410174220220022001491b41c4889122200041a2c48811491b0b4c01017f2000410036020c200041106a2003360200200104402001101521040b20002004360200200020042002413c6c6a2202360208200020042001413c6c6a36020c2000200236020420000b900101027f200028020421022000280200210303402002200346450440200128020441446a200241446a220210162001200128020441446a3602040c010b0b200028020021022000200128020436020020012002360204200028020421022000200128020836020420012002360208200028020821022000200128020c3602082001200236020c200120012802043602000b2b01027f20002802082101200028020421020340200120024704402000200141446a22013602080c010b0b0b1200200020012802182002413c6c6a10131a0ba10101037f20004200370200200041086a2202410036020020012d0000410171450440200020012902003702002002200141086a28020036020020000f0b20012802082103024020012802042201410a4d0440200020014101743a0000200041016a21020c010b200141106a4170712204101721022000200136020420002004410172360200200020023602080b2002200320011051200120026a41003a000020000b1500200020012802182002413c6c6a41246a10131a0b09002000413c6c10170ba4010020002001290200370200200041086a200141086a2802003602002001100b200041146a200141146a2802003602002000200129020c37020c2001410c6a100b200041206a200141206a28020036020020002001290218370218200141186a100b2000412c6a2001412c6a28020036020020002001290224370224200141246a100b200041386a200141386a28020036020020002001290230370230200141306a100b0b0b002000410120001b10190b8c0402057f017e230041c0016b2200240010071001220110192202100220004180016a200020022001101a22014100101b20004180016a101c0240024020004180016a101d450d00200028028401450d002000280280012d000041c001490d010b10000b200041406b20004180016a101e2000280244220241094f044010000b200028024021030340200204402002417f6a210220033100002005420886842105200341016a21030c010b0b024002402005500d00418008101f200551044020004180016a102010210c020b418508101f2005510440200041406b413c10221a200041406b1023210220004180016a20014101101b20004180016a20021024200041186a1020200041186a20004180016a2002100d100c10210c020b419808101f200551044020004180016a10202000419c016a28020021032000280298012104200041186a10252101200041d8006a4100360200200041d0006a4200370300200041c8006a420037030020004200370340200041406b200320046b413c6d41ff0171ad2205102620002802402103200041406b4104721027200120031028200120051029220128020c200141106a28020047044010000b200128020020012802041006200128020c22030440200120033602100b10210c020b41b008101f200551044020014102102a0c020b41c808101f2005520d0020014103102a0c010b10000b102b200041c0016a24000b970101047f230041106b220124002001200036020c2000047f41900b200041086a2202411076220041900b2802006a2203360200418c0b2002418c0b28020022026a41076a417871220436020002400240200341107420044d044041900b200341016a360200200041016a21000c010b2000450d010b200040000d0010000b20022001410c6a4104103a41086a0541000b200141106a24000b0c00200020012002411c102c0bc90202077f017e230041106b220324002001280208220520024b0440200341086a2001102f20012003280208200328020c103036020c20032001102f410021052001027f410020032802002206450d001a410020032802042208200128020c2207490d001a200820072007417f461b210420060b360210200141146a2004360200200141003602080b200141106a210903402001280214210402402005200249044020040d01410021040b2000200928020020044114102c1a200341106a24000f0b20032001102f41002104027f410020032802002207450d001a410020032802042208200128020c2206490d001a200820066b2104200620076a0b21052001200436021420012005360210200320094100200520041030105520012003290300220a3702102001200128020c200a422088a76a36020c2001200128020841016a22053602080c000b000b4101017f200028020445044010000b0240200028020022012d0000418101470d00200028020441014d047f100020002802000520010b2c00014100480d0010000b0b980101037f200028020445044041000f0b2000101c200028020022022c0000220141004e044020014100470f0b027f4101200141807f460d001a200141ff0171220341b7014d0440200028020441014d047f100020002802000520020b2d00014100470f0b4100200341bf014b0d001a2000280204200141ff017141ca7e6a22014d047f100020002802000520020b20016a2d00004100470b0bd40101047f2001102d2204200128020422024b04401000200128020421020b200128020021052000027f02400240200204404100210120052c00002203417f4a0d01027f200341ff0171220141bf014d04404100200341ff017141b801490d011a200141c97e6a0c010b4100200341ff017141f801490d001a200141897e6a0b41016a21010c010b4101210120050d000c010b410021032002200149200120046a20024b720d00410020022004490d011a200120056a2103200220016b20042004417f461b0c010b41000b360204200020033602000b3901027e42a5c688a1c89ca7f94b210103402000300000220250450440200041016a2100200142b383808080207e20028521010c010b0b20010bb107010c7f230041c0016b2204240020004200370218200042b1fdc48289b7f690b67f3703102000410036020820004200370200200041206a4100360200200441186a102522082000290310102e200828020c200841106a28020047044010000b200041186a21072000411c6a210a0240200828020022032008280204220510032206450d002006101721020340200120026a41003a00002006200141016a2201470d000b20032005200220011004417f460440410021010c010b024002402004200241016a200120026a2002417f736a101a2202280204450d0020022802002d000041c001490d00200441306a2002102f2004280234210141002103034020010440200441002004280230220520052001103022096a20054520012009497222051b3602304100200120096b20051b2101200341016a21030c010b0b2000280220200028021822016b413c6d20034904402007200441306a2003200028021c20016b413c6d200041206a100f22011010200110110b20044198016a200241011031210320044188016a200241001031210b200041206a210c200328020421010340200b280204200146410020032802082202200b280208461b0d02200441f0006a20012002411c102c200441306a1023220110240240200028021c22022000280220490440200220011016200a200a280200413c6a3602000c010b200441a8016a2007200220072802006b413c6d220241016a100e2002200c100f210220042802b00120011016200420042802b001413c6a3602b001200720021010200210110b20032003280204220120032802086a410020011b22013602042003280200220204402003200236020820012002103021092003027f200328020422024504404100210541000c010b410021054100200328020822012009490d001a200120092009417f461b210520020b2201ad2005ad42208684370204200341002003280200220220056b2205200520024b1b36020005200341003602080b0c000b000b10000b200621010b200828020c22060440200820063602100b024020010d0020002802042202200028020022016b413c6d22032000280220200028021822066b413c6d4d04402003200a28020020066b413c6d22034b0440200120012003413c6c6a2201200610321a20012002200a10330c020b2007200120022006103210340c010b200604402007103520004100360220200042003702180b200020072003100e2203101522063602182000200636021c200020062003413c6c6a36022020012002200a10330b200441c0016a240020000be20701107f230041f0016b22012400200141186a10252106200141e8016a22024100360200200141e0016a22054200370300200141d8016a22044200370300200142003703d001200141d0016a2000290310102620012802d0012103200141d0016a410472102720062003102820062000290310102e200628020c200641106a28020047044010000b200628020421082006280200200110252103200241003602002005420037030020044200370300200142003703d001027f20002802182000411c6a280200460440200141013602d00141010c010b200141d0016a410010362104200028021c200028021822026b2105037f2005047f200441001036220720021037200720014190016a200241246a10131038200141d0006a200241306a10131038410110361a200541446a21052002413c6a21020c01052004410110361a20012802d0010b0b0b2105200141d0016a410472102741011017220241fe013a0000200328020c200341106a28020047044010000b2003280204220441016a220720032802084b047f20032007103920032802040520040b20032802006a20024101103a1a2003200328020441016a3602042003200241016a200520026b6a10282003200028021c20002802186b413c6d103b2107200028021c200028021822026b2105200141d0006a410472210a20014190016a410472210b200141d0016a410472210c03402005044020074103103b210420014100360268200142003703602001420037035820014200370350200141d0006a20021037200141d0006a200141406b200241246a220d10131038200141306a200241306a220e101310381a20042001280250102820044103103b2104200141003602a801200142003703a0012001420037039801200142003703900120014190016a2002103c20014190016a20014180016a2002410c6a220f10131038200141f0006a200241186a2210101310381a2004200128029001102820044101103b2104200141003602e801200142003703e001200142003703d801200142003703d001200141d0016a200141c0016a2002101310381a200420012802d00110282004200141b0016a20021013103d200c1027200141d0016a200f1013103d200141c0016a20101013103d200b1027200141d0016a200d1013103d20014190016a200e1013103d1a200a1027200541446a21052002413c6a21020c010b0b0240200328020c2003280210460440200328020021020c010b100020032802002102200328020c2003280210460d0010000b2008200220032802041005200328020c22020440200320023602100b200628020c22030440200620033602100b200041186a103e2000103e200141f0016a24000be10201027f02402001450d00200041003a0000200020016a2202417f6a41003a000020014103490d00200041003a0002200041003a00012002417d6a41003a00002002417e6a41003a000020014107490d00200041003a00032002417c6a41003a000020014109490d002000410020006b41037122036a220241003602002002200120036b417c7122036a2201417c6a410036020020034109490d002002410036020820024100360204200141786a4100360200200141746a410036020020034119490d002002410036021820024100360214200241003602102002410036020c200141706a41003602002001416c6a4100360200200141686a4100360200200141646a41003602002003200241047141187222036b2101200220036a2102034020014120490d0120024200370300200241186a4200370300200241106a4200370300200241086a4200370300200241206a2102200141606a21010c000b000b20000b2400200010082000410c6a1008200041186a1008200041246a1008200041306a100820000ba80101017f230041d0006b22022400200241086a20004100101b200241206a200241086a4100101b200241386a200241206a4100101b200241386a2001103f200241386a200241086a4101101b200241386a2001410c6a103f200241386a200241086a4102101b200241386a200141186a103f200241386a20004101101b200241386a200141246a103f200241386a20004102101b200241386a200141306a103f200241d0006a24000b29002000410036020820004200370200200041001039200041146a41003602002000420037020c20000b840102027f017e4101210320014280015a0440034020012004845045044020044238862001420888842101200241016a2102200442088821040c010b0b200241384f047f2002104020026a0520020b41016a21030b200041186a28020022020440200041086a280200200041146a2802002002104121000b2000200028020020036a3602000bea0101047f230041106b22042400200028020422012000280210220341087641fcffff07716a2102027f410020012000280208460d001a2002280200200341ff07714102746a0b2101200441086a20001042200428020c210303400240200120034604402000410036021420002802082102200028020421010340200220016b41027522034103490d022000200141046a22013602040c000b000b200141046a220120022802006b418020470d0120022802042101200241046a21020c010b0b2003417f6a220241014d04402000418004418008200241016b1b3602100b200020011043200441106a24000b1300200028020820014904402000200110390b0bbc0202037f037e02402001500440200041800110440c010b20014280015a044020012107034020062007845045044020064238862007420888842107200241016a2102200642088821060c010b0b0240200241384f04402002210303402003044020034108762103200441016a21040c010b0b200441c9004f044010000b2000200441b77f6a41ff017110442000200028020420046a1057200028020420002802006a417f6a21042002210303402003450d02200420033a0000200341087621032004417f6a21040c000b000b200020024180017341ff017110440b2000200028020420026a1057200028020420002802006a417f6a210203402001200584500d02200220013c0000200542388620014208888421012002417f6a2102200542088821050c000b000b20002001a741ff017110440b20004101104720000bb30201037f23004180016b22022400200220004101101b2002101c024002402002101d450d002002280204450d0020022802002d000041c001490d010b10000b200241e0006a2002101e2002280264220041024f044010000b200228026021030340200004402000417f6a210020032d00002104200341016a21030c010b0b20021020200241286a200220042001110400200241386a10252100200241f8006a4100360200200241f0006a4200370300200241e8006a420037030020024200370360200241e0006a200241d0006a200241286a101310382002280260210441046a10272000200410282000200241e0006a200241286a1013103d220028020c200041106a28020047044010000b200028020020002802041006200028020c22010440200020013602100b102120024180016a24000b880101037f41ec08410136020041f0082802002100034020000440034041f40841f4082802002201417f6a2202360200200141014845044041ec084100360200200020024102746a22004184016a280200200041046a28020011010041ec08410136020041f00828020021000c010b0b41f408412036020041f008200028020022003602000c010b0b0b730020004200370210200042ffffffff0f370208200020023602042000200136020002402003410871450d002000105320024f0d002003410471044010000c010b200042003702000b02402003411071450d002000105320024d0d0020034104710440100020000f0b200042003702000b20000bff0201037f200028020445044041000f0b2000101c41012102024020002802002c00002201417f4a0d00200141ff0171220341b7014d0440200341807f6a0f0b02400240200141ff0171220141bf014d0440024020002802042201200341c97e6a22024d047f100020002802040520010b4102490d0020002802002d00010d0010000b200241054f044010000b20002802002d000145044010000b4100210241b7012101034020012003460440200241384f0d030c0405200028020020016a41ca7e6a2d00002002410874722102200141016a21010c010b000b000b200141f7014d0440200341c07e6a0f0b024020002802042201200341897e6a22024d047f100020002802040520010b4102490d0020002802002d00010d0010000b200241054f044010000b20002802002d000145044010000b4100210241f701210103402001200346044020024138490d0305200028020020016a418a7e6a2d00002002410874722102200141016a21010c010b0b0b200241ff7d490d010b10000b20020b09002000200110291a0b2101017f2001102d220220012802044b044010000b2000200120011054200210550b2301017f230041206b22022400200241086a200020014114102c1053200241206a24000be70101037f230041106b2204240020004200370200200041086a410036020020012802042103024002402002450440200321020c010b410021022003450d002003210220012802002d000041c001490d00200441086a2001102f20004100200428020c2201200428020822022001103022032003417f461b20024520012003497222031b220536020820004100200220031b3602042000200120056b3602000c010b20012802002103200128020421012000410036020020004100200220016b20034520022001497222021b36020820004100200120036a20021b3602040b200441106a240020000b6901037f200120006b21054100210103402001200546450440200120026a2203200020016a2204104f2003410c6a2004410c6a104f200341186a200441186a104f200341246a200441246a104f200341306a200441306a104f2001413c6a21010c010b0b200120026a0b2e000340200020014645044020022802002000100d1a20022002280200413c6a3602002000413c6a21000c010b0b0b0900200020013602040b0b002000200028020010340bbf0c02077f027e230041306b22052400200041046a2107027f20014101460440200041086a280200200041146a280200200041186a220228020022041041280200210120022004417f6a360200200710484180104f044020072000410c6a280200417c6a10430b200141384f047f2001104020016a0520010b41016a2101200041186a28020022020440200041086a280200200041146a280200200210410c020b20000c010b0240200710480d00200041146a28020022014180084f0440200020014180786a360214200041086a2201280200220228020021042001200241046a360200200520043602182007200541186a10490c010b2000410c6a2802002202200041086a2802006b4102752204200041106a2203280200220620002802046b220141027549044041802010172104200220064704400240200028020c220120002802102206470d0020002802082202200028020422034b04402000200220012002200220036b41027541016a417e6d41027422036a104a220136020c2000200028020820036a3602080c010b200541186a200620036b2201410175410120011b22012001410276200041106a104b2102200028020c210320002802082101034020012003470440200228020820012802003602002002200228020841046a360208200141046a21010c010b0b200029020421092000200229020037020420022009370200200029020c21092000200229020837020c200220093702082002104c200028020c21010b200120043602002000200028020c41046a36020c0c020b02402000280208220120002802042206470d00200028020c2202200028021022034904402000200120022002200320026b41027541016a41026d41027422036a104d22013602082000200028020c20036a36020c0c010b200541186a200320066b2201410175410120011b2201200141036a410276200041106a104b2102200028020c210320002802082101034020012003470440200228020820012802003602002002200228020841046a360208200141046a21010c010b0b200029020421092000200229020037020420022009370200200029020c21092000200229020837020c200220093702082002104c200028020821010b2001417c6a2004360200200020002802082201417c6a22023602082002280200210220002001360208200520023602182007200541186a10490c010b20052001410175410120011b20042003104b210241802010172106024020022802082201200228020c2208470d0020022802042204200228020022034b04402002200420012004200420036b41027541016a417e6d41027422036a104a22013602082002200228020420036a3602040c010b200541186a200820036b2201410175410120011b22012001410276200241106a280200104b21042002280208210320022802042101034020012003470440200428020820012802003602002004200428020841046a360208200141046a21010c010b0b2002290200210920022004290200370200200420093702002002290208210920022004290208370208200420093702082004104c200228020821010b200120063602002002200228020841046a360208200028020c2104034020002802082004460440200028020421012000200228020036020420022001360200200228020421012002200436020420002001360208200029020c21092000200229020837020c200220093702082002104c052004417c6a210402402002280204220120022802002208470d0020022802082203200228020c22064904402002200120032003200620036b41027541016a41026d41027422066a104d22013602042002200228020820066a3602080c010b200541186a200620086b2201410175410120011b2201200141036a4102762002280210104b2002280208210620022802042101034020012006470440200528022020012802003602002005200528022041046a360220200141046a21010c010b0b20022902002109200220052903183702002002290208210a20022005290320370208200520093703182005200a370320104c200228020421010b2001417c6a200428020036020020022002280204417c6a3602040c010b0b0b200541186a20071042200528021c410036020041012101200041186a0b2202200228020020016a360200200541306a240020000b4001017f230041206b2202240020004100103622002001103c2000200241106a2001410c6a101310382002200141186a10131038410110361a200241206a24000ba10101037f41012103024002400240200128020420012d00002202410176200241017122041b220241014d0440200241016b0d032001280208200141016a20041b2c0000417f4c0d010c030b200241374b0d010b200241016a21030c010b2002104020026a41016a21030b027f200041186a28020022010440200041086a280200200041146a280200200110410c010b20000b2201200128020020036a36020020000b2f01017f200028020820014904402001101920002802002000280204103a210220002001360208200020023602000b0bf80801067f0340200020046a2105200120046a220341037145200220044672450440200520032d00003a0000200441016a21040c010b0b200220046b210602402005410371220845044003402006411049450440200020046a2202200120046a2203290200370200200241086a200341086a290200370200200441106a2104200641706a21060c010b0b027f2006410871450440200120046a2103200020046a0c010b200020046a2202200120046a2201290200370200200141086a2103200241086a0b21042006410471044020042003280200360200200341046a2103200441046a21040b20064102710440200420032f00003b0000200341026a2103200441026a21040b2006410171450d01200420032d00003a000020000f0b024020064120490d002008417f6a220841024b0d00024002400240024002400240200841016b0e020102000b2005200120046a220628020022033a0000200541016a200641016a2f00003b0000200041036a2108200220046b417d6a2106034020064111490d03200420086a2202200120046a220541046a2802002207410874200341187672360200200241046a200541086a2802002203410874200741187672360200200241086a2005410c6a28020022074108742003411876723602002002410c6a200541106a2802002203410874200741187672360200200441106a2104200641706a21060c000b000b2005200120046a220628020022033a0000200541016a200641016a2d00003a0000200041026a2108200220046b417e6a2106034020064112490d03200420086a2202200120046a220541046a2802002207411074200341107672360200200241046a200541086a2802002203411074200741107672360200200241086a2005410c6a28020022074110742003411076723602002002410c6a200541106a2802002203411074200741107672360200200441106a2104200641706a21060c000b000b2005200120046a28020022033a0000200041016a21082004417f7320026a2106034020064113490d03200420086a2202200120046a220541046a2802002207411874200341087672360200200241046a200541086a2802002203411874200741087672360200200241086a2005410c6a28020022074118742003410876723602002002410c6a200541106a2802002203411874200741087672360200200441106a2104200641706a21060c000b000b200120046a41036a2103200020046a41036a21050c020b200120046a41026a2103200020046a41026a21050c010b200120046a41016a2103200020046a41016a21050b20064110710440200520032d00003a00002005200328000136000120052003290005370005200520032f000d3b000d200520032d000f3a000f200541106a2105200341106a21030b2006410871044020052003290000370000200541086a2105200341086a21030b2006410471044020052003280000360000200541046a2105200341046a21030b20064102710440200520032f00003b0000200541026a2105200341026a21030b2006410171450d00200520032d00003a00000b20000b840201057f2001044020002802042104200041106a2802002202200041146a280200220349044020022001ad2004ad422086843702002000200028021041086a36021020000f0b027f41002002200028020c22026b410375220541016a2206200320026b2202410275220320032006491b41ffffffff01200241037541ffffffff00491b2203450d001a200341037410170b2102200220054103746a22052001ad2004ad4220868437020020052000280210200028020c22066b22016b2104200141014e0440200420062001103a1a0b2000200220034103746a3602142000200541086a3602102000200436020c20000f0b200041c00110442000410041004101104620000b2701017f230041106b220224002000410010362002200110131038410110361a200241106a24000b910101047f410121022001280208200141016a20012d0000220441017122051b210302400240024002402001280204200441017620051b2201410146044020032c000022014100480d012000200141ff017110440c040b200141374b0d01200121020b200020024180017341ff017110440c010b200020011045200121020b200020032002410010460b20004101104720000b0e0020002802000440200010350b0bf30201057f230041206b22022400024002402000280204044020002802002d000041c001490d010b200241086a10080c010b200241186a2000101e2000102d21030240024002400240200228021822000440200228021c220420034f0d010b41002100200241106a410036020020024200370308410021040c010b200241106a4100360200200242003703082000200420032003417f461b22046a21052004410a4b0d010b200220044101743a0008200241086a41017221030c010b200441106a4170712206101721032002200436020c20022006410172360208200220033602100b03402000200546450440200320002d00003a0000200341016a2103200041016a21000c010b0b200341003a00000b024020012d0000410171450440200141003b01000c010b200128020841003a00002001410036020420012d0000410171450d00200141003602000b20012002290308370200200141086a200241106a280200360200200241086a100b200241206a24000b1e01017f03402000044020004108762100200141016a21010c010b0b20010b25002000200120026a417f6a220141087641fcffff07716a280200200141ff07714102746a0b4f01037f20012802042203200128021020012802146a220441087641fcffff07716a21022000027f410020032001280208460d001a2002280200200441ff07714102746a0b360204200020023602000b2501017f200028020821020340200120024645044020002002417c6a22023602080c010b0b0b2500200041011056200028020020002802046a20013a00002000200028020441016a3602040b5e01027f20011040220241b7016a22034180024e044010000b2000200341ff017110442000200028020420026a1057200028020420002802006a417f6a2100034020010440200020013a0000200141087621012000417f6a21000c010b0b0b2d00200020021056200028020020002802046a20012002103a1a2000200028020420026a3602042000200310470b820201047f02402001450d00034020002802102202200028020c460d01200241786a28020020014904401000200028021021020b200241786a2203200328020020016b220136020020010d012000200336021020004101200028020422042002417c6a28020022016b22021040220341016a20024138491b220520046a1057200120002802006a220420056a20042002104e0240200241374d0440200028020020016a200241406a3a00000c010b200341f7016a220441ff014d0440200028020020016a20043a00002000280200200120036a6a210103402002450d02200120023a0000200241087621022001417f6a21010c000b000b10000b410121010c000b000b0b2801017f200028020820002802046b2201410874417f6a410020011b200028021420002802106a6b0ba10202057f017e230041206b22052400024020002802082202200028020c2206470d0020002802042203200028020022044b04402000200320022003200320046b41027541016a417e6d41027422046a104a22023602082000200028020420046a3602040c010b200541086a200620046b2202410175410120021b220220024102762000410c6a104b2103200028020821042000280204210203402002200446450440200328020820022802003602002003200328020841046a360208200241046a21020c010b0b2000290200210720002003290200370200200320073702002000290208210720002003290208370208200320073702082003104c200028020821020b200220012802003602002000200028020841046a360208200541206a24000b2501017f200120006b2201410275210320010440200220002001104e0b200220034102746a0b4f01017f2000410036020c200041106a2003360200200104402001410274101721040b200020043602002000200420024102746a22023602082000200420014102746a36020c2000200236020420000b2b01027f200028020821012000280204210203402001200247044020002001417c6a22013602080c010b0b0b1b00200120006b22010440200220016b220220002001104e0b20020b8d0301037f024020002001460d00200120006b20026b410020024101746b4d0440200020012002103a1a0c010b20002001734103712103027f024020002001490440200020030d021a410021030340200120036a2104200020036a2205410371450440200220036b210241002103034020024104490d04200320056a200320046a280200360200200341046a21032002417c6a21020c000b000b20022003460d04200520042d00003a0000200341016a21030c000b000b024020030d002001417f6a21030340200020026a22044103714504402001417c6a21032000417c6a2104034020024104490d03200220046a200220036a2802003602002002417c6a21020c000b000b2002450d042004417f6a200220036a2d00003a00002002417f6a21020c000b000b2001417f6a210103402002450d03200020026a417f6a200120026a2d00003a00002002417f6a21020c000b000b200320046a2101200320056a0b210303402002450d01200320012d00003a00002002417f6a2102200341016a2103200141016a21010c000b000b0b880201047f20002001470440200128020420012d00002202410176200241017122031b2102200141016a21042001280208410a2101200420031b210420002d0000410171220304402000280200417e71417f6a21010b200220014d0440027f2003044020002802080c010b200041016a0b210120020440200120042002104e0b200120026a41003a000020002d00004101710440200020023602040f0b200020024101743a00000f0b416f2103200141e6ffffff074d0440410b20014101742201200220022001491b220141106a4170712001410b491b21030b200310172201200420021051200020023602042000200341017236020020002001360208200120026a41003a00000b0b3501017f230041106b220041a08b0436020c41880b200028020c41076a4178712200360200418c0b200036020041900b3f003602000b100020020440200020012002103a1a0b0b3801017f41fc0a420037020041840b410036020041742100034020000440200041880b6a4100360200200041046a21000c010b0b4104100a0b2e01017f200028020445044041000f0b4101210120002802002c0000417f4c047f200010542000102d6a0541010b0b5b00027f027f41002000280204450d001a410020002802002c0000417f4a0d011a20002802002d0000220041bf014d04404100200041b801490d011a200041c97e6a0c010b4100200041f801490d001a200041897e6a0b41016a0b0b5a01027f2000027f0240200128020022054504400c010b200220036a200128020422014b2001200249720d00410020012003490d011a200220056a2104200120026b20032003417f461b0c010b41000b360204200020043602000b1b00200028020420016a220120002802084b04402000200110390b0b0f00200020011039200020013602040b0b6601004180080b5f696e6974006164645f7375625f6d795f6d657373616765006765745f7375625f6d795f6d6573736167655f73697a65006765745f7375625f6d795f6d6573736167655f68656164006765745f7375625f6d795f6d6573736167655f66726f6d";

    public static String BINARY = BINARY_0;

    public static final String FUNC_ADD_SUB_MY_MESSAGE = "add_sub_my_message";

    public static final String FUNC_GET_SUB_MY_MESSAGE_SIZE = "get_sub_my_message_size";

    public static final String FUNC_GET_SUB_MY_MESSAGE_HEAD = "get_sub_my_message_head";

    public static final String FUNC_GET_SUB_MY_MESSAGE_FROM = "get_sub_my_message_from";

    protected TwoInherit(String contractAddress, Web3j web3j, Credentials credentials, GasProvider contractGasProvider, Long chainId) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider, chainId);
    }

    protected TwoInherit(String contractAddress, Web3j web3j, TransactionManager transactionManager, GasProvider contractGasProvider, Long chainId) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider, chainId);
    }

    public static RemoteCall<TwoInherit> deploy(Web3j web3j, Credentials credentials, GasProvider contractGasProvider, Long chainId) {
        String encodedConstructor = WasmFunctionEncoder.encodeConstructor(BINARY, Arrays.asList());
        return deployRemoteCall(TwoInherit.class, web3j, credentials, contractGasProvider, encodedConstructor, chainId);
    }

    public static RemoteCall<TwoInherit> deploy(Web3j web3j, TransactionManager transactionManager, GasProvider contractGasProvider, Long chainId) {
        String encodedConstructor = WasmFunctionEncoder.encodeConstructor(BINARY, Arrays.asList());
        return deployRemoteCall(TwoInherit.class, web3j, transactionManager, contractGasProvider, encodedConstructor, chainId);
    }

    public static RemoteCall<TwoInherit> deploy(Web3j web3j, Credentials credentials, GasProvider contractGasProvider, BigInteger initialVonValue, Long chainId) {
        String encodedConstructor = WasmFunctionEncoder.encodeConstructor(BINARY, Arrays.asList());
        return deployRemoteCall(TwoInherit.class, web3j, credentials, contractGasProvider, encodedConstructor, initialVonValue, chainId);
    }

    public static RemoteCall<TwoInherit> deploy(Web3j web3j, TransactionManager transactionManager, GasProvider contractGasProvider, BigInteger initialVonValue, Long chainId) {
        String encodedConstructor = WasmFunctionEncoder.encodeConstructor(BINARY, Arrays.asList());
        return deployRemoteCall(TwoInherit.class, web3j, transactionManager, contractGasProvider, encodedConstructor, initialVonValue, chainId);
    }

    public RemoteCall<TransactionReceipt> add_sub_my_message(Sub_my_message sub_one_message) {
        final WasmFunction function = new WasmFunction(FUNC_ADD_SUB_MY_MESSAGE, Arrays.asList(sub_one_message), Void.class);
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> add_sub_my_message(Sub_my_message sub_one_message, BigInteger vonValue) {
        final WasmFunction function = new WasmFunction(FUNC_ADD_SUB_MY_MESSAGE, Arrays.asList(sub_one_message), Void.class);
        return executeRemoteCallTransaction(function, vonValue);
    }

    public RemoteCall<Uint8> get_sub_my_message_size() {
        final WasmFunction function = new WasmFunction(FUNC_GET_SUB_MY_MESSAGE_SIZE, Arrays.asList(), Uint8.class);
        return executeRemoteCall(function, Uint8.class);
    }

    public RemoteCall<String> get_sub_my_message_head(Uint8 index) {
        final WasmFunction function = new WasmFunction(FUNC_GET_SUB_MY_MESSAGE_HEAD, Arrays.asList(index), String.class);
        return executeRemoteCall(function, String.class);
    }

    public RemoteCall<String> get_sub_my_message_from(Uint8 index) {
        final WasmFunction function = new WasmFunction(FUNC_GET_SUB_MY_MESSAGE_FROM, Arrays.asList(index), String.class);
        return executeRemoteCall(function, String.class);
    }

    public static TwoInherit load(String contractAddress, Web3j web3j, Credentials credentials, GasProvider contractGasProvider, Long chainId) {
        return new TwoInherit(contractAddress, web3j, credentials, contractGasProvider, chainId);
    }

    public static TwoInherit load(String contractAddress, Web3j web3j, TransactionManager transactionManager, GasProvider contractGasProvider, Long chainId) {
        return new TwoInherit(contractAddress, web3j, transactionManager, contractGasProvider, chainId);
    }

    public static class Message {
        public String head;
    }

    public static class My_message {
        public Message baseClass;

        public String body;

        public String end;
    }

    public static class Sub_my_message {
        public My_message baseClass;

        public String from;

        public String to;
    }
}
