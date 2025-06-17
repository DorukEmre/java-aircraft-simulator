SHELL		= /bin/sh

NAME		= avaj_launcher
SOURCES	= $(shell find . -name "*.java")
TARGET	= sources.txt

all: compile

sources.txt:
    echo "$(SOURCES)" > $(TARGET)

compile: sources.txt
    javac @sources.txt

clean:
    rm -f $(TARGET)
    find . -name "*.class" -delete
