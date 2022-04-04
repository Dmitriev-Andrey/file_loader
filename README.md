# File loader
The tool for loader files. Analog `wget` on Linux. 

#### Supported protocols
1. HTTP
2. HTTPS
3. FTP
4. SFTP

### Building
Maven is used for the build. Next command:
```sh
mvn clean package
```
Final package: `target/file_loader.jar`

### Running
Use java for running. Example:
```sh
java -jar target/file_loader.jar -r 2 -l /home/marbok/tmp/ -t 2 http://ftp.gnu.org/gnu/wget/wget-1.5.3.tar.gz https://ftp.gnu.org/gnu/=README ftp://user:123@localhost/index.html sftp://demo:demo@localhost/result.html
```

### Arguments
```sh
Usage: file_loader [options] List of download URLs
  Options:
    --help, -h

    --location, -l
      Download location.
      Default: current location
    --retries, -r
      Count of retries.
      Default: 1
    --threads, -t
      Count of thread for working
      Default: 1
```

### Adding a new protocol
1. Add protocol in `url/Protocol.java`
2. Create new loader for the protocol in directory `loaders`. The loader must extend `loaders/Loader.java`
3. Add new loader in `loaders/LoaderFactory.java`