# centos based java7 and postgresql container
FROM centos:latest
MAINTAINER poulmotiram@gmail.com

ADD script/run.sh /bin/run.sh
RUN chmod +x /bin/run.sh

RUN yum update -y && yum install -y java-1.7.0-openjdk.x86_64 postgresql-server
RUN su postgres -c "initdb -D /var/lib/pgsql/data"
# RUN su postgres -c "cd /var/lib/pgsql/data && pg_ctl -D /var/lib/pgsql/data -l logfile start"

EXPOSE 5432

CMD ["/bin/run.sh"]


