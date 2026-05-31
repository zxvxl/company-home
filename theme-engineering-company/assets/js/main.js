/**
 * 水利工程企业官网主题 - 主脚本
 * Theme: theme-engineering-company
 * Version: 1.0.0
 */

(function () {
  'use strict';

  document.addEventListener('DOMContentLoaded', function () {

    // ========================================
    // Mobile Navigation Toggle
    // ========================================
    var navToggle = document.getElementById('navToggle');
    var navMenu = document.getElementById('navMenu');

    if (navToggle && navMenu) {
      navToggle.addEventListener('click', function () {
        navToggle.classList.toggle('active');
        navMenu.classList.toggle('active');
        document.body.style.overflow = navMenu.classList.contains('active') ? 'hidden' : '';
      });

      // Close menu when clicking a link
      var navLinks = navMenu.querySelectorAll('.nav-link');
      navLinks.forEach(function (link) {
        link.addEventListener('click', function () {
          navToggle.classList.remove('active');
          navMenu.classList.remove('active');
          document.body.style.overflow = '';
        });
      });

      // Close menu when clicking outside
      document.addEventListener('click', function (e) {
        if (!navMenu.contains(e.target) && !navToggle.contains(e.target)) {
          navToggle.classList.remove('active');
          navMenu.classList.remove('active');
          document.body.style.overflow = '';
        }
      });
    }

    // ========================================
    // Sticky Header
    // ========================================
    var siteHeader = document.getElementById('siteHeader');
    var scrollThreshold = 100;

    function handleScroll() {
      if (window.scrollY > scrollThreshold) {
        siteHeader.classList.add('scrolled');
      } else {
        siteHeader.classList.remove('scrolled');
      }
    }

    if (siteHeader) {
      window.addEventListener('scroll', handleScroll, { passive: true });
      handleScroll(); // Check initial state
    }

    // ========================================
    // Banner Carousel
    // ========================================
    var heroBanner = document.getElementById('heroBanner');

    if (heroBanner) {
      var slides = heroBanner.querySelectorAll('.banner-slide');
      var dots = heroBanner.querySelectorAll('.dot');
      var prevBtn = document.getElementById('bannerPrev');
      var nextBtn = document.getElementById('bannerNext');
      var currentSlide = 0;
      var slideCount = slides.length;
      var autoPlayInterval = null;
      var autoPlayDelay = 5000;

      function showSlide(index) {
        if (index >= slideCount) index = 0;
        if (index < 0) index = slideCount - 1;

        slides.forEach(function (slide) {
          slide.classList.remove('active');
        });
        dots.forEach(function (dot) {
          dot.classList.remove('active');
        });

        slides[index].classList.add('active');
        if (dots[index]) {
          dots[index].classList.add('active');
        }
        currentSlide = index;
      }

      function nextSlide() {
        showSlide(currentSlide + 1);
      }

      function prevSlide() {
        showSlide(currentSlide - 1);
      }

      function startAutoPlay() {
        stopAutoPlay();
        autoPlayInterval = setInterval(nextSlide, autoPlayDelay);
      }

      function stopAutoPlay() {
        if (autoPlayInterval) {
          clearInterval(autoPlayInterval);
          autoPlayInterval = null;
        }
      }

      // Event listeners
      if (nextBtn) {
        nextBtn.addEventListener('click', function () {
          nextSlide();
          startAutoPlay();
        });
      }

      if (prevBtn) {
        prevBtn.addEventListener('click', function () {
          prevSlide();
          startAutoPlay();
        });
      }

      // Dot click handlers
      dots.forEach(function (dot, index) {
        dot.addEventListener('click', function () {
          showSlide(index);
          startAutoPlay();
        });
      });

      // Pause on hover
      heroBanner.addEventListener('mouseenter', stopAutoPlay);
      heroBanner.addEventListener('mouseleave', startAutoPlay);

      // Start auto play
      startAutoPlay();
    }

    // ========================================
    // Scroll Counter Animation
    // ========================================
    var statsSection = document.getElementById('statsSection');

    if (statsSection) {
      var counters = statsSection.querySelectorAll('.stat-number');
      var animated = false;

      function animateCounters() {
        if (animated) return;
        animated = true;

        counters.forEach(function (counter) {
          var target = parseInt(counter.getAttribute('data-target'), 10);
          var duration = 2000;
          var startTime = null;

          function updateCounter(timestamp) {
            if (!startTime) startTime = timestamp;
            var progress = Math.min((timestamp - startTime) / duration, 1);
            // Ease out cubic
            var easeProgress = 1 - Math.pow(1 - progress, 3);
            var current = Math.floor(easeProgress * target);
            counter.textContent = current;

            if (progress < 1) {
              requestAnimationFrame(updateCounter);
            } else {
              counter.textContent = target;
            }
          }

          requestAnimationFrame(updateCounter);
        });
      }

      // Use IntersectionObserver for scroll-triggered animation
      if ('IntersectionObserver' in window) {
        var observer = new IntersectionObserver(function (entries) {
          entries.forEach(function (entry) {
            if (entry.isIntersecting) {
              animateCounters();
              observer.unobserve(entry.target);
            }
          });
        }, {
          threshold: 0.3
        });

        observer.observe(statsSection);
      } else {
        // Fallback for older browsers
        window.addEventListener('scroll', function () {
          var rect = statsSection.getBoundingClientRect();
          if (rect.top < window.innerHeight && rect.bottom > 0) {
            animateCounters();
          }
        });
      }
    }

    // ========================================
    // Smooth Scroll for Anchor Links
    // ========================================
    var anchorLinks = document.querySelectorAll('a[href^="#"]');
    anchorLinks.forEach(function (link) {
      link.addEventListener('click', function (e) {
        var targetId = this.getAttribute('href');
        if (targetId === '#') return;

        var targetElement = document.querySelector(targetId);
        if (targetElement) {
          e.preventDefault();
          var headerOffset = 120;
          var elementPosition = targetElement.getBoundingClientRect().top;
          var offsetPosition = elementPosition + window.pageYOffset - headerOffset;

          window.scrollTo({
            top: offsetPosition,
            behavior: 'smooth'
          });
        }
      });
    });

    // ========================================
    // Back to Top Button
    // ========================================
    var backToTop = document.getElementById('backToTop');

    if (backToTop) {
      var showThreshold = 300;

      window.addEventListener('scroll', function () {
        if (window.scrollY > showThreshold) {
          backToTop.classList.add('visible');
        } else {
          backToTop.classList.remove('visible');
        }
      }, { passive: true });

      backToTop.addEventListener('click', function () {
        window.scrollTo({
          top: 0,
          behavior: 'smooth'
        });
      });
    }

    // ========================================
    // Active Navigation Highlighting
    // ========================================
    var currentPath = window.location.pathname;
    var navItems = document.querySelectorAll('.nav-link[data-page]');

    navItems.forEach(function (item) {
      var href = item.getAttribute('href');
      if (currentPath === href || (href !== '/' && currentPath.startsWith(href))) {
        item.classList.add('active');
      } else if (currentPath === '/' && href === '/') {
        item.classList.add('active');
      }
    });

    // ========================================
    // Lazy Loading Placeholder for Images
    // ========================================
    if ('IntersectionObserver' in window) {
      var lazyImages = document.querySelectorAll('img[data-src]');

      var imageObserver = new IntersectionObserver(function (entries) {
        entries.forEach(function (entry) {
          if (entry.isIntersecting) {
            var img = entry.target;
            img.src = img.getAttribute('data-src');
            img.removeAttribute('data-src');
            img.classList.add('loaded');
            imageObserver.unobserve(img);
          }
        });
      }, {
        rootMargin: '50px 0px'
      });

      lazyImages.forEach(function (img) {
        imageObserver.observe(img);
      });
    } else {
      // Fallback: load all images immediately
      var lazyImages = document.querySelectorAll('img[data-src]');
      lazyImages.forEach(function (img) {
        img.src = img.getAttribute('data-src');
        img.removeAttribute('data-src');
      });
    }

    // ========================================
    // Fade-in Animation on Scroll
    // ========================================
    if ('IntersectionObserver' in window) {
      var animateElements = document.querySelectorAll('.section-header, .business-card, .case-card, .news-card');

      var animateObserver = new IntersectionObserver(function (entries) {
        entries.forEach(function (entry) {
          if (entry.isIntersecting) {
            entry.target.classList.add('animate-fadeInUp');
            animateObserver.unobserve(entry.target);
          }
        });
      }, {
        threshold: 0.1
      });

      animateElements.forEach(function (el) {
        animateObserver.observe(el);
      });
    }

  });
})();
