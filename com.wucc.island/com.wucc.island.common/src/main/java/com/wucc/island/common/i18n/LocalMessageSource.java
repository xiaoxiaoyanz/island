package com.wucc.island.common.i18n;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;

import java.util.Locale;

//@Component
public class LocalMessageSource {

    private final Logger logger = LoggerFactory.getLogger(LocalMessageSource.class);

    @Autowired
    private MessageSource i18nMessageSrouce;

    private static final Locale LOCALE = Locale.CHINA;

    public String getMessage(String code) {
        return getMessage(code, null);
    }


    /**
     * Try to resolve the message. Return default message if no message was found.
     *
     * @param code           the code to lookup up, such as 'calculator.noRateSet'. Users of
     *                       this class are encouraged to base message names on the relevant fully
     *                       qualified class name, thus avoiding conflict and ensuring maximum clarity.
     * @param args           array of arguments that will be filled in for params within
     *                       the message (params look like "{0}", "{1,date}", "{2,time}" within a message),
     *                       or {@code null} if none.
     * @param defaultMessage String to return if the lookup fails
     * @return the resolved message if the lookup was successful;
     * otherwise the default message passed as a parameter
     * @see java.text.MessageFormat
     */
    public String getMessage(String code, Object[] args, String defaultMessage) {
        return i18nMessageSrouce.getMessage(code, args, defaultMessage, LOCALE);
    }


    /**
     * Try to resolve the message. Return default message if no message was found.
     *
     * @param code           the code to lookup up, such as 'calculator.noRateSet'. Users of
     *                       this class are encouraged to base message names on the relevant fully
     *                       qualified class name, thus avoiding conflict and ensuring maximum clarity.
     * @param args           array of arguments that will be filled in for params within
     *                       the message (params look like "{0}", "{1,date}", "{2,time}" within a message),
     *                       or {@code null} if none.
     * @param defaultMessage String to return if the lookup fails
     * @param locale         the Locale in which to do the lookup
     * @return the resolved message if the lookup was successful;
     * otherwise the default message passed as a parameter
     * @see java.text.MessageFormat
     */
    public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
        return i18nMessageSrouce.getMessage(code, args, defaultMessage, locale);
    }

    /**
     * Try to resolve the message. Treat as an error if the message can't be found.
     *
     * @param code the code to lookup up, such as 'calculator.noRateSet'
     * @param args Array of arguments that will be filled in for params within
     *             the message (params look like "{0}", "{1,date}", "{2,time}" within a message),
     *             or {@code null} if none.
     * @return the resolved message
     * @throws NoSuchMessageException if the message wasn't found
     * @see java.text.MessageFormat
     */
    public String getMessage(String code, Object[] args) {
        try {
            return i18nMessageSrouce.getMessage(code, args, LOCALE);
        } catch (NoSuchMessageException e) {
            logger.error(code + "编码不存在！");
            return "";
        }
    }


    /**
     * Try to resolve the message. Treat as an error if the message can't be found.
     *
     * @param code   the code to lookup up, such as 'calculator.noRateSet'
     * @param args   Array of arguments that will be filled in for params within
     *               the message (params look like "{0}", "{1,date}", "{2,time}" within a message),
     *               or {@code null} if none.
     * @param locale the Locale in which to do the lookup
     * @return the resolved message
     * @throws NoSuchMessageException if the message wasn't found
     * @see java.text.MessageFormat
     */
    public String getMessage(String code, Object[] args, Locale locale) {
        try {
            return i18nMessageSrouce.getMessage(code, args, locale);
        } catch (NoSuchMessageException e) {
            logger.error(code + "编码不存在！");
            return "";
        }
    }
}
