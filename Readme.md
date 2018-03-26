The purpose of this project is to 

1. Export all data redaction attributes (RegEx Patterns, Code Identifiers, Classes and Packages) from a SAAS server
2. Import them into a new environment (also a SAAS server)

To execute this DataRedaction from command prompt, you need a JDK and Maven

1.  Go to the DataRedaction Folder and execute
```
mvn clean compile package
```
2.  To run, execute
    ```
    ./run.sh
    ```

This will prompt you for From Credentials, From ServiceID, To Credentials and To ServiceID.

Sample output is below
```

~/Documents/workspace/Overops/DataRedaction> ./run.sh
2018-03-25 20:34:59 [main] INFO  CredentialsManager:20 - Lets get the Overops Credentials to Export from:
Lets get the Overops Credentials to Export From:
Username: karthik.lalithraj@takipi.com
Password:
ServiceID: S35110
2018-03-25 20:35:30 [main] INFO  CredentialsManager:32 - Lets get the Overops Credentials to Import To:
Lets get the Overops Credentials to Import To:
Username: karthik.lalithraj@overops.com
Password:
ServiceID: S35134
2018-03-25 20:35:48 [main] INFO  DataRedactionPropertyReader:30 - Loading properties from file: /DataRedaction.properties
2018-03-25 20:35:49 [main] INFO  GetAPI:30 - Successfully able to export All Data Redaction from host.
2018-03-25 20:35:49 [main] INFO  PutAPI:25 - Importing All Data Redaction Attributes ...
2018-03-25 20:35:49 [main] INFO  PutAPI:43 - Lets handle attributeType: identifiers
2018-03-25 20:36:01 [main] INFO  PutAPI:45 - Successfully able to import All Data Redaction for attributeType: identifiers
2018-03-25 20:36:01 [main] WARN  PutAPI:47 - Nothing found for attributeType: classes
2018-03-25 20:36:01 [main] WARN  PutAPI:47 - Nothing found for attributeType: packages
2018-03-25 20:36:01 [main] INFO  PutAPI:43 - Lets handle attributeType: patterns
2018-03-25 20:36:03 [main] INFO  PutAPI:45 - Successfully able to import All Data Redaction for attributeType: patterns
2018-03-25 20:36:03 [main] INFO  PutAPI:34 - Import complete.

```

Good Luck.