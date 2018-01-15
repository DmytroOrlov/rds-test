# docker build -t rdstest_web-app .
FROM dmytroorlov/jdk

HEALTHCHECK --interval=5s --timeout=2s --retries=2 \
  CMD curl --silent --fail http://localhost:9000/health || exit 1

WORKDIR /root

COPY production.conf /root/production.conf
COPY target/universal/stage /root

EXPOSE 9000

CMD ["/root/bin/rds-test", "-Dconfig.file=/root/production.conf", "-Dpidfile.path=/dev/null"]
