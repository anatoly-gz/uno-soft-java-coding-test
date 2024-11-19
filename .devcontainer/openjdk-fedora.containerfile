FROM quay.io/fedora/fedora:latest

ARG JAVA_VERSION='latest'
RUN dnf install -y --setopt=install_weak_deps=False \
    which unzip git "java-$JAVA_VERSION-openjdk-devel" \
    && dnf clean all

ARG GRADLE_VERSION='none'
RUN if [ "$GRADLE_VERSION" != 'none' ]; then \
    curl -sSL -o gradle.zip "https://services.gradle.org/distributions/gradle-$GRADLE_VERSION-bin.zip" && \
    unzip -d /opt gradle.zip && rm gradle.zip && \
    echo "PATH=\$PATH:/opt/gradle-$GRADLE_VERSION/bin" > /etc/profile.d/gradle.sh; fi

ARG USER='root'
RUN if [ $USER != 'root' ]; then useradd -G wheel $USER; fi
USER $USER
