Usage:
 1. Add FailFinishListener to Main Test Class , which should be executed

 2. logback.xml - use LogbackToAllureAppender like ussual appender ConsoleAppender, for example:
     <appender name="LOGBACK-TO-ALLURE"
               class="ru.yandex.qatools.allure.logback.appender.LogbackToAllureAppender">
         <layout class="ch.qos.logback.classic.PatternLayout">
             <Pattern>
                 %d{HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n
             </Pattern>
         </layout>
     </appender>

==========================================
Идеи / Предложения
Однопоточно
1. Нужно сделать добавление к AllureStepsAspects на этапе компиляции - DONE 
2. Добавить использование PatternLayout для формирования текста лога с временем и именем классов
3. Заиспользовать вместо FailFinishListener аспект на @Test - DONE for testng
4. Заиспользовать вместо FailFinishListener аспект на @Test для junit
5. Заиспользовать вместо FailFinishListener аспект на @Test для junit + log4j - вероятно 2 библиотеки нужно делать

Многопоточность
1. Использовать переменные Thread Local чтобы Steps из разных Thread друг с другом не пересекались

==========================================
Issue :
1. Сообщения из предыдущего теста попадают в текущий
Тест : LoggingToAllureTest.executeTestSimple

2. Сообщения из одного Step попадают в другой - не всегда срабатывает flush
Тест : LoggingToAllureTest.twoDiffSteps01