package com.ubo.tp.twitub.datamodel.jaxb;

import com.ubo.tp.twitub.common.Constants;
import com.ubo.tp.twitub.datamodel.jaxb.bean.twit.TwitXml;
import com.ubo.tp.twitub.datamodel.jaxb.bean.user.UserXml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;

import static com.sun.activation.registries.LogSupport.log;

/**
 * Classe de lecture des fichiers XML.
 *
 * @author S.Lucas
 */
public class JaxbReader {

    public JaxbReader() {
    }

    protected static final String JAXB_BEAN_ROOT_PACKAGE = "com.ubo.tp.twitub.datamodel.jaxb.bean";

    protected static final String JAXB_TWIT_BEAN_PACKAGE = JAXB_BEAN_ROOT_PACKAGE + "." + "twit";

    protected static final String JAXB_USER_BEAN_PACKAGE = JAXB_BEAN_ROOT_PACKAGE + "." + "user";

    /**
     * Lecture du fichier XML pour un {@link TwitXml}
     *
     * @param twitFileName
     */
    public static TwitXml readTwit(String twitFileName) {
        TwitXml twit = null;

        if (twitFileName != null && twitFileName.endsWith(Constants.TWIT_FILE_EXTENSION)) {
            twit = (TwitXml) readFile(twitFileName, JAXB_TWIT_BEAN_PACKAGE);
        }

        return twit;
    }

    /**
     * Lecture du fichier XML pour un {@link UserXml}
     *
     * @param userFileName
     */
    public static UserXml readUser(String userFileName) {
        UserXml user = null;

        if (userFileName != null && userFileName.endsWith(Constants.USER_FILE_EXTENSION)) {
            user = (UserXml) readFile(userFileName, JAXB_USER_BEAN_PACKAGE);
        }

        return user;
    }

    /**
     * Unmarshalling du fichier XML
     *
     * @param xmlFileName , Fichier XML à lire
     * @param beanPackage , Package contenant les bean JAXB.
     */
    protected static Object readFile(String xmlFileName, String beanPackage) {
        Object object = null;
        try {
            JAXBContext context;
            context = JAXBContext.newInstance(beanPackage);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            object = unmarshaller.unmarshal(new FileReader(xmlFileName));
        } catch (Throwable t) {
           log("Erreur de chargement du fichier : '" + xmlFileName + "'");
            t.printStackTrace();
        }
        return object;
    }

}
