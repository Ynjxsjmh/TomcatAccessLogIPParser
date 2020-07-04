package com.tomcat.access.log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "Parse Tomcat RSDB Log", mixinStandardHelpOptions = true, description = "Get the number of occurrences of the country code corresponding to the IP address in RSDB log.")
public class ParseLog implements Callable<Integer> {
    @Option(names = { "-f", "--file" }, required = true)
    private static String fileName = "";

    @Option(names = { "-d", "--directory" }, required = false)
    private static String fileDir = "";

    private static String map = "{\"id\": {\"count\": \"0\", \"name\": \"Indonesia\"},\"pg\": {\"count\": \"0\", \"name\": \"Papua New Guinea\"},\"mx\": {\"count\": \"0\", \"name\": \"Mexico\"},\"ee\": {\"count\": \"0\", \"name\": \"Estonia\"},\"dz\": {\"count\": \"0\", \"name\": \"Algeria\"},\"ma\": {\"count\": \"0\", \"name\": \"Morocco\"},\"mr\": {\"count\": \"0\", \"name\": \"Mauritania\"},\"sn\": {\"count\": \"0\", \"name\": \"Senegal\"},\"gm\": {\"count\": \"0\", \"name\": \"Gambia\"},\"gw\": {\"count\": \"0\", \"name\": \"Guinea-Bissau\"},\"gn\": {\"count\": \"0\", \"name\": \"Guinea\"},\"sl\": {\"count\": \"0\", \"name\": \"Sierra Leone\"},\"lr\": {\"count\": \"0\", \"name\": \"Liberia\"},\"ci\": {\"count\": \"0\", \"name\": \"Cote d'Ivoire\"},\"ml\": {\"count\": \"0\", \"name\": \"Mali\"},\"bf\": {\"count\": \"0\", \"name\": \"Burkina Faso\"},\"ne\": {\"count\": \"0\", \"name\": \"Niger\"},\"gh\": {\"count\": \"0\", \"name\": \"Ghana\"},\"tg\": {\"count\": \"0\", \"name\": \"Togo\"},\"bj\": {\"count\": \"0\", \"name\": \"Benin\"},\"ng\": {\"count\": \"0\", \"name\": \"Nigeria\"},\"tn\": {\"count\": \"0\", \"name\": \"Tunisia\"},\"ly\": {\"count\": \"0\", \"name\": \"Libya\"},\"eg\": {\"count\": \"0\", \"name\": \"Egypt\"},\"td\": {\"count\": \"0\", \"name\": \"Chad\"},\"sd\": {\"count\": \"0\", \"name\": \"Sudan\"},\"cm\": {\"count\": \"0\", \"name\": \"Cameroon\"},\"er\": {\"count\": \"0\", \"name\": \"Eritrea\"},\"dj\": {\"count\": \"0\", \"name\": \"Djibouti\"},\"et\": {\"count\": \"0\", \"name\": \"Ethiopia\"},\"so\": {\"count\": \"0\", \"name\": \"Somalia\"},\"ye\": {\"count\": \"0\", \"name\": \"Yemen\"},\"cf\": {\"count\": \"0\", \"name\": \"Central African Republic\"},\"st\": {\"count\": \"0\", \"name\": \"Sao Tome and Principe\"},\"gq\": {\"count\": \"0\", \"name\": \"Equatorial Guinea\"},\"ga\": {\"count\": \"0\", \"name\": \"Gabon\"},\"cg\": {\"count\": \"0\", \"name\": \"Congo\"},\"ao\": {\"count\": \"0\", \"name\": \"Angola\"},\"cd\": {\"count\": \"0\", \"name\": \"Congo\"},\"rw\": {\"count\": \"0\", \"name\": \"Rwanda\"},\"bi\": {\"count\": \"0\", \"name\": \"Burundi\"},\"ug\": {\"count\": \"0\", \"name\": \"Uganda\"},\"ke\": {\"count\": \"0\", \"name\": \"Kenya\"},\"tz\": {\"count\": \"0\", \"name\": \"Tanzania\"},\"zm\": {\"count\": \"0\", \"name\": \"Zambia\"},\"mw\": {\"count\": \"0\", \"name\": \"Malawi\"},\"mz\": {\"count\": \"0\", \"name\": \"Mozambique\"},\"zw\": {\"count\": \"0\", \"name\": \"Zimbabwe\"},\"na\": {\"count\": \"0\", \"name\": \"Namibia\"},\"bw\": {\"count\": \"0\", \"name\": \"Botswana\"},\"sz\": {\"count\": \"0\", \"name\": \"Swaziland\"},\"ls\": {\"count\": \"0\", \"name\": \"Lesotho\"},\"za\": {\"count\": \"0\", \"name\": \"South Africa\"},\"gl\": {\"count\": \"0\", \"name\": \"Greenland\"},\"au\": {\"count\": \"0\", \"name\": \"Australia\"},\"nz\": {\"count\": \"0\", \"name\": \"New Zealand\"},\"nc\": {\"count\": \"0\", \"name\": \"New Caledonia\"},\"my\": {\"count\": \"0\", \"name\": \"Malaysia\"},\"bn\": {\"count\": \"0\", \"name\": \"Brunei Darussalam\"},\"tl\": {\"count\": \"0\", \"name\": \"Timor-Leste\"},\"sb\": {\"count\": \"0\", \"name\": \"Solomon Islands\"},\"vu\": {\"count\": \"0\", \"name\": \"Vanuatu\"},\"fj\": {\"count\": \"0\", \"name\": \"Fiji\"},\"ph\": {\"count\": \"0\", \"name\": \"Philippines\"},\"cn\": {\"count\": \"0\", \"name\": \"China\"},\"tw\": {\"count\": \"0\", \"name\": \"Taiwan\"},\"jp\": {\"count\": \"0\", \"name\": \"Japan\"},\"ru\": {\"count\": \"0\", \"name\": \"Russian Federation\"},\"us\": {\"count\": \"0\", \"name\": \"United States of America\"},\"mu\": {\"count\": \"0\", \"name\": \"Mauritius\"},\"re\": {\"count\": \"0\", \"name\": \"Reunion\"},\"mg\": {\"count\": \"0\", \"name\": \"Madagascar\"},\"km\": {\"count\": \"0\", \"name\": \"Comoros\"},\"sc\": {\"count\": \"0\", \"name\": \"Seychelles\"},\"mv\": {\"count\": \"0\", \"name\": \"Maldives\"},\"pt\": {\"count\": \"0\", \"name\": \"Portugal\"},\"es\": {\"count\": \"0\", \"name\": \"Spain\"},\"cv\": {\"count\": \"0\", \"name\": \"Cape Verde\"},\"pf\": {\"count\": \"0\", \"name\": \"French Polynesia\"},\"kn\": {\"count\": \"0\", \"name\": \"Saint Kitts and Nevis\"},\"ag\": {\"count\": \"0\", \"name\": \"Antigua and Barbuda\"},\"dm\": {\"count\": \"0\", \"name\": \"Dominica\"},\"lc\": {\"count\": \"0\", \"name\": \"Saint Lucia\"},\"bb\": {\"count\": \"0\", \"name\": \"Barbados\"},\"gd\": {\"count\": \"0\", \"name\": \"Grenada\"},\"tt\": {\"count\": \"0\", \"name\": \"Trinidad and Tobago\"},\"do\": {\"count\": \"0\", \"name\": \"Dominican Republic\"},\"ht\": {\"count\": \"0\", \"name\": \"Haiti\"},\"fk\": {\"count\": \"0\", \"name\": \"Falkland Islands\"},\"is\": {\"count\": \"0\", \"name\": \"Iceland\"},\"no\": {\"count\": \"0\", \"name\": \"Norway\"},\"lk\": {\"count\": \"0\", \"name\": \"Sri Lanka\"},\"cu\": {\"count\": \"0\", \"name\": \"Cuba\"},\"bs\": {\"count\": \"0\", \"name\": \"Bahamas\"},\"jm\": {\"count\": \"0\", \"name\": \"Jamaica\"},\"ec\": {\"count\": \"0\", \"name\": \"Ecuador\"},\"ca\": {\"count\": \"0\", \"name\": \"Canada\"},\"gt\": {\"count\": \"0\", \"name\": \"Guatemala\"},\"hn\": {\"count\": \"0\", \"name\": \"Honduras\"},\"sv\": {\"count\": \"0\", \"name\": \"El Salvador\"},\"ni\": {\"count\": \"0\", \"name\": \"Nicaragua\"},\"cr\": {\"count\": \"0\", \"name\": \"Costa Rica\"},\"pa\": {\"count\": \"0\", \"name\": \"Panama\"},\"co\": {\"count\": \"0\", \"name\": \"Colombia\"},\"ve\": {\"count\": \"0\", \"name\": \"Venezuela\"},\"gy\": {\"count\": \"0\", \"name\": \"Guyana\"},\"sr\": {\"count\": \"0\", \"name\": \"Suriname\"},\"gf\": {\"count\": \"0\", \"name\": \"French Guiana\"},\"pe\": {\"count\": \"0\", \"name\": \"Peru\"},\"bo\": {\"count\": \"0\", \"name\": \"Bolivia\"},\"py\": {\"count\": \"0\", \"name\": \"Paraguay\"},\"uy\": {\"count\": \"0\", \"name\": \"Uruguay\"},\"ar\": {\"count\": \"0\", \"name\": \"Argentina\"},\"cl\": {\"count\": \"0\", \"name\": \"Chile\"},\"br\": {\"count\": \"0\", \"name\": \"Brazil\"},\"bz\": {\"count\": \"0\", \"name\": \"Belize\"},\"mn\": {\"count\": \"0\", \"name\": \"Mongolia\"},\"kp\": {\"count\": \"0\", \"name\": \"North Korea\"},\"kr\": {\"count\": \"0\", \"name\": \"South Korea\"},\"kz\": {\"count\": \"0\", \"name\": \"Kazakhstan\"},\"tm\": {\"count\": \"0\", \"name\": \"Turkmenistan\"},\"uz\": {\"count\": \"0\", \"name\": \"Uzbekistan\"},\"tj\": {\"count\": \"0\", \"name\": \"Tajikistan\"},\"kg\": {\"count\": \"0\", \"name\": \"Kyrgyz Republic\"},\"af\": {\"count\": \"0\", \"name\": \"Afghanistan\"},\"pk\": {\"count\": \"0\", \"name\": \"Pakistan\"},\"in\": {\"count\": \"0\", \"name\": \"India\"},\"np\": {\"count\": \"0\", \"name\": \"Nepal\"},\"bt\": {\"count\": \"0\", \"name\": \"Bhutan\"},\"bd\": {\"count\": \"0\", \"name\": \"Bangladesh\"},\"mm\": {\"count\": \"0\", \"name\": \"Myanmar\"},\"th\": {\"count\": \"0\", \"name\": \"Thailand\"},\"kh\": {\"count\": \"0\", \"name\": \"Cambodia\"},\"la\": {\"count\": \"0\", \"name\": \"Lao People's Democratic Republic\"},\"vn\": {\"count\": \"0\", \"name\": \"Vietnam\"},\"ge\": {\"count\": \"0\", \"name\": \"Georgia\"},\"am\": {\"count\": \"0\", \"name\": \"Armenia\"},\"az\": {\"count\": \"0\", \"name\": \"Azerbaijan\"},\"ir\": {\"count\": \"0\", \"name\": \"Iran\"},\"tr\": {\"count\": \"0\", \"name\": \"Turkey\"},\"om\": {\"count\": \"0\", \"name\": \"Oman\"},\"ae\": {\"count\": \"0\", \"name\": \"United Arab Emirates\"},\"qa\": {\"count\": \"0\", \"name\": \"Qatar\"},\"kw\": {\"count\": \"0\", \"name\": \"Kuwait\"},\"sa\": {\"count\": \"0\", \"name\": \"Saudi Arabia\"},\"sy\": {\"count\": \"0\", \"name\": \"Syrian Arab Republic\"},\"iq\": {\"count\": \"0\", \"name\": \"Iraq\"},\"jo\": {\"count\": \"0\", \"name\": \"Jordan\"},\"lb\": {\"count\": \"0\", \"name\": \"Lebanon\"},\"il\": {\"count\": \"0\", \"name\": \"Israel\"},\"cy\": {\"count\": \"0\", \"name\": \"Cyprus\"},\"gb\": {\"count\": \"0\", \"name\": \"United Kingdom\"},\"ie\": {\"count\": \"0\", \"name\": \"Ireland\"},\"se\": {\"count\": \"0\", \"name\": \"Sweden\"},\"fi\": {\"count\": \"0\", \"name\": \"Finland\"},\"lv\": {\"count\": \"0\", \"name\": \"Latvia\"},\"lt\": {\"count\": \"0\", \"name\": \"Lithuania\"},\"by\": {\"count\": \"0\", \"name\": \"Belarus\"},\"pl\": {\"count\": \"0\", \"name\": \"Poland\"},\"it\": {\"count\": \"0\", \"name\": \"Italy\"},\"fr\": {\"count\": \"0\", \"name\": \"France\"},\"nl\": {\"count\": \"0\", \"name\": \"Netherlands\"},\"be\": {\"count\": \"0\", \"name\": \"Belgium\"},\"de\": {\"count\": \"0\", \"name\": \"Germany\"},\"dk\": {\"count\": \"0\", \"name\": \"Denmark\"},\"ch\": {\"count\": \"0\", \"name\": \"Switzerland\"},\"cz\": {\"count\": \"0\", \"name\": \"Czech Republic\"},\"sk\": {\"count\": \"0\", \"name\": \"Slovakia\"},\"at\": {\"count\": \"0\", \"name\": \"Austria\"},\"hu\": {\"count\": \"0\", \"name\": \"Hungary\"},\"si\": {\"count\": \"0\", \"name\": \"Slovenia\"},\"hr\": {\"count\": \"0\", \"name\": \"Croatia\"},\"ba\": {\"count\": \"0\", \"name\": \"Bosnia and Herzegovina\"},\"mt\": {\"count\": \"0\", \"name\": \"Malta\"},\"ua\": {\"count\": \"0\", \"name\": \"Ukraine\"},\"md\": {\"count\": \"0\", \"name\": \"Moldova\"},\"ro\": {\"count\": \"0\", \"name\": \"Romania\"},\"rs\": {\"count\": \"0\", \"name\": \"Serbia\"},\"bg\": {\"count\": \"0\", \"name\": \"Bulgaria\"},\"al\": {\"count\": \"0\", \"name\": \"Albania\"},\"mk\": {\"count\": \"0\", \"name\": \"Macedonia\"},\"gr\": {\"count\": \"0\", \"name\": \"Greece\"}}";

    public static void main(String[] args) {
        int exitCode = new CommandLine(new ParseLog()).execute(args);
        System.exit(exitCode);
    }

    private String parseSingleFile(String map, final String fileName) {
        Gson gson = new Gson();
        JsonElement element = gson.fromJson(map, JsonElement.class);
        JsonObject jsonMap = element.getAsJsonObject();

        Set<String> ipSet = AccessLogUtil.getUniqueIP(fileName);

        for (String ip : ipSet) {
            String countryCode = GeoIpUtil.getCountryCode(ip);
            try {
                JsonObject country = jsonMap.get(countryCode.toLowerCase()).getAsJsonObject();
                int count = country.get("count").getAsInt() + 1;
                country.addProperty("count", count);
            } catch (java.lang.NullPointerException e) {
                System.err.printf("Couldn't resolve: ip=%s, countryName=%s, countryCode=%s%n", ip,
                        GeoIpUtil.getCountryName(ip), GeoIpUtil.getCountryCode(ip));
                e.printStackTrace();
            }
        }

        return jsonMap.toString();
    }

    private String parseDirectory(String map, final String directory) {
        File folder = new File(directory);
        List<String> fileNameList = new ArrayList<String>();
        this.listFilesForFolder(folder, fileNameList);

        for (String fileName : fileNameList) {
            map = this.parseSingleFile(map, fileName);
        }

        return map;
    }

    private void listFilesForFolder(final File folder, List<String> fileNameList) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                this.listFilesForFolder(fileEntry, fileNameList);
            } else {
                fileNameList.add(fileEntry.getAbsolutePath());
            }
        }
    }

    @Override
    public Integer call() throws Exception {
        String result = "";

        if (!fileName.isEmpty()) {
            result = this.parseSingleFile(map, fileName);
        }

        if (!fileDir.isEmpty()) {
            result = this.parseDirectory(map, fileDir);
        }

        System.out.println(result);

        return 0;
    }
}
