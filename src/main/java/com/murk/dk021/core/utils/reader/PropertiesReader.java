package com.murk.dk021.core.utils.reader;

import com.sun.org.apache.xml.internal.utils.WrongParserException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

/**
 * Created by win1.
 *
 * Утильный класс для чтения .property файлов
 */
public final class PropertiesReader
{
    public PropertiesReader()
    {
    }

    /**
     * Чтение одной записи в пропертей
     *
     * @param path путь к файлу
     * @param key ключь в файле
     *
     * @return значение ключа.
     */

    public static synchronized String  readProperty(String path, String key)
    {
        try(Reader reader = new InputStreamReader(new FileInputStream(path)))
        {
            Properties property = new Properties();
            property.load(reader);
            return property.getProperty(key);
        }
        catch (IOException e)
        {
            throw new WrongParserException("Файл или ключь к файлу не найден. Путь - "+path+", ключь - "+ key);
        }
    }
}