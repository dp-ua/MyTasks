class Settings {
    //Класс для работы с настройками
    //принимаем в конструкторе имя файла, с коротрым будет работать
    //и список настроек, которые будут использованы как по-умолчанию, если файла настроек нет


    private String fileName
    private Properties properties = new Properties()
    private Map<String, String> defaultSettings = new HashMap<>()

    Settings(String fileName, Map<String, String> defaultSettings) {
        //конструктор. Принимает введеные параметры и пытается загрузить параметры из файла.
        //если файл не существует - загружает настройки по умолчанию и сохраняет их
        this.fileName = fileName
        this.defaultSettings = defaultSettings
        properties.putAll(defaultSettings)
        loadSettings()
        saveSettings()
    }

    void resetToDefault() {
        //сброс настроек к параметрам по-умолчанию
        properties.clear()
        properties.putAll(defaultSettings)
        saveSettings()
    }

    void setProperty(String key, String value) {
        //устанавливаем нужный параметр
        properties.setProperty(key, value);
        saveSettings()
    }

    String getProperty(String key) {
        //получаем нужный параметр
        //если такого параметра нет - возвращаем ""
        return properties.get(key, "")
    }


    private def loadSettings() {
        //загружаем настройки из файла
        try {
            File file = new File(fileName)
            FileInputStream inputStream = new FileInputStream(file)
            properties.load(inputStream);
            inputStream.close()
            return true
        } catch (FileNotFoundException e) {
            e.printStackTrace()
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false
    }

    private def saveSettings() {
        //Сохраняем настройки в файл
        try {
            File file = new File(fileName)
            FileOutputStream out = new FileOutputStream(file)
            properties.store(out, null);
            out.close()
            return true
        } catch (Exception e) {
            e.printStackTrace()
        }
        return false
    }
}
